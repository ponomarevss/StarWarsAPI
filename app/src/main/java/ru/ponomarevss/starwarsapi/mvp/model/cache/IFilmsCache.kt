package ru.ponomarevss.starwarsapi.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film

interface IFilmsCache {
    fun putFilms(films: List<Film>): Completable
    fun getFilms(): Single<List<Film>>
}