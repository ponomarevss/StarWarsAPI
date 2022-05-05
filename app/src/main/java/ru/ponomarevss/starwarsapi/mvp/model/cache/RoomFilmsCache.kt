package ru.ponomarevss.starwarsapi.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilm
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilmCharacter
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.db.Database

class RoomFilmsCache(val db: Database) : IFilmsCache {
    override fun putFilms(films: List<Film>): Completable = Completable.fromAction {
        films.map { film ->
            with(film) {
                db.filmDao.insert(
                    RoomFilm(
                        fUrl = url,
                        title = title,
                        episodeId = episodeId,
                        director = director,
                        producer = producer,
                        releaseDate = releaseDate
                    )
                )
                characters.map { characterUrl ->
                    db.filmCharacterDao.insert(
                        RoomFilmCharacter(
                            filmUrl = url,
                            characterUrl = characterUrl))
                }
            }
        }
    }

    override fun getFilms(): Single<List<Film>> = Single.fromCallable {
        db.filmDao.getAll().map { roomFilm ->
            val cUrlList = db.filmCharacterDao.findForFilmUrl(roomFilm.fUrl).map { it.characterUrl }
            with(roomFilm) {
                Film(
                    title = title,
                    episodeId = episodeId,
                    director = director,
                    producer = producer,
                    releaseDate = releaseDate,
                    characters = cUrlList,
                    url = fUrl
                )
            }
        }
    }
}