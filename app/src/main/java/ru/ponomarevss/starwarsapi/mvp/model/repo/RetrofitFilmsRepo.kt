package ru.ponomarevss.starwarsapi.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.ponomarevss.starwarsapi.mvp.model.api.IDataSource
import ru.ponomarevss.starwarsapi.mvp.model.cache.IFilmsCache
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.model.network.INetworkStatus
import java.io.IOException

class RetrofitFilmsRepo(val api: IDataSource, private val networkStatus: INetworkStatus, val cache: IFilmsCache) : IFilmsRepo {
    override fun getFilms(): Single<List<Film>> = cache.getFilms().flatMap { filmsList ->
        if (filmsList.isEmpty()) {
            //если список пустой, забираем фильмы из сети
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    //получаем фильмы от сервера
                    api.getFilms().flatMap {
                        //сохраняем в кэш
                        cache.putFilms(it.results).subscribe()
//                        cache.getFilms()
                        //возвращаем непустой список от сервера
                        Single.just(it.results)
                    }
                } else {
                    throw IOException("Network status is offline")
                }
            }
        } else {
            //возвращаем непустой список
            Single.just(filmsList)
        }
    }.subscribeOn(Schedulers.io())
}