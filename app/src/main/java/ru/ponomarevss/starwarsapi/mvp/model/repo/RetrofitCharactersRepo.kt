package ru.ponomarevss.starwarsapi.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.ponomarevss.starwarsapi.mvp.model.api.IDataSource
import ru.ponomarevss.starwarsapi.mvp.model.cache.ICharactersCache
import ru.ponomarevss.starwarsapi.mvp.model.entity.Character
import ru.ponomarevss.starwarsapi.mvp.model.network.INetworkStatus
import java.io.IOException

class RetrofitCharactersRepo(val api: IDataSource, private val networkStatus: INetworkStatus, val cache: ICharactersCache) : ICharactersRepo {

    override fun getCharacters(characters: List<String>): Single<List<Character>> = Single.fromCallable {
        characters.map { url ->
            //получаем персонажа из кэша
            cache.getCharacter(url)
                .onErrorResumeNext {
                    //если нет, запрашиваем из сети
                    networkStatus.isOnlineSingle().flatMap { isOnline ->
                        if (isOnline) {
                            //получаем с сервера
                            api.getCharacter(url).flatMap { character ->
                                //сохраняем в кэш
                                cache.putCharacter(character).subscribe()
                                //возвращаем
                                Single.just(character)
                            }
                        } else {
                            throw IOException("Network status is offline")
                        }
                    }
                }
                .blockingGet()
        }
    }.subscribeOn(Schedulers.io())
}