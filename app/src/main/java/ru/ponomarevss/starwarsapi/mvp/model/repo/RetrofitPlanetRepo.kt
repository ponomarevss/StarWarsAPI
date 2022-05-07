package ru.ponomarevss.starwarsapi.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.ponomarevss.starwarsapi.mvp.model.api.IDataSource
import ru.ponomarevss.starwarsapi.mvp.model.cache.IPlanetCache
import ru.ponomarevss.starwarsapi.mvp.model.entity.Planet
import ru.ponomarevss.starwarsapi.mvp.model.network.INetworkStatus
import java.io.IOException

class RetrofitPlanetRepo(val api: IDataSource, private val networkStatus: INetworkStatus, val cache: IPlanetCache): IPlanetRepo {
    override fun getPlanet(planetUrl: String): Single<Planet> = cache.getPlanet(planetUrl).onErrorResumeNext {
        //если нет в кэше, запрашиваем из сети
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                //получаем с сервера
                api.getPlanet(planetUrl).flatMap { planet ->
                    //сохраняем в кэш
                    cache.putPlanet(planet).subscribe()
                    //возвращаем
                    Single.just(planet)
                }

            } else {
                throw IOException("Network status is offline")
            }
        }
    }.subscribeOn(Schedulers.io())
}