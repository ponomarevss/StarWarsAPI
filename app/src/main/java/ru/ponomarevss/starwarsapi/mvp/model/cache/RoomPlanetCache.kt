package ru.ponomarevss.starwarsapi.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Planet
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomPlanet
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.db.Database

class RoomPlanetCache(val db: Database) : IPlanetCache {
    override fun putPlanet(planet: Planet): Completable = Completable.fromAction {
        with(planet) {
            db.planetDao.insert(
                RoomPlanet(
                    pUrl = url,
                    name = name,
                    diameter = diameter,
                    climate = climate,
                    gravity = gravity,
                    terrain = terrain,
                    population = population
                )
            )
        }
    }

    override fun getPlanet(url: String): Single<Planet> = Single.fromCallable{
        val roomPlanet = db.planetDao.findForUrl(url)
        roomPlanet?.let {
            Planet(
                name = it.name,
                diameter = it.diameter,
                climate = it.climate,
                gravity = it.gravity,
                terrain = it.terrain,
                population = it.population,
                url = it.pUrl
            )
        }
    }
}