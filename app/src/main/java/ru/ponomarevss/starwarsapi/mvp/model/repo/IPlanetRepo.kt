package ru.ponomarevss.starwarsapi.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Planet

interface IPlanetRepo {
    fun getPlanet(planetUrl: String): Single<Planet>
}