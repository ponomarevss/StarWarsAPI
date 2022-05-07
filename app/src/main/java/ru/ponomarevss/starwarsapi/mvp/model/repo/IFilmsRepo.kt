package ru.ponomarevss.starwarsapi.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film

interface IFilmsRepo {
    fun getFilms(): Single<List<Film>>
}