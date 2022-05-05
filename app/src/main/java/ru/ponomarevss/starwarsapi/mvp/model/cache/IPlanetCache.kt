package ru.ponomarevss.starwarsapi.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Planet

interface IPlanetCache {
    fun putPlanet(planet: Planet): Completable
    fun getPlanet(url: String): Single<Planet>
}