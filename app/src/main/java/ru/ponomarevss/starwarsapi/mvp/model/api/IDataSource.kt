package ru.ponomarevss.starwarsapi.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.ponomarevss.starwarsapi.mvp.model.entity.Character
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.model.entity.FilmsResponse
import ru.ponomarevss.starwarsapi.mvp.model.entity.Planet

interface IDataSource {

    @GET("films/")
    fun getFilms(): Single<FilmsResponse>

    @GET
    fun getCharacter(@Url url: String): Single<Character>

    @GET
    fun getPlanet(@Url url: String): Single<Planet>
}