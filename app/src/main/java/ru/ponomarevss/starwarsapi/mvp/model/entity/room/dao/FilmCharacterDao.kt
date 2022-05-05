package ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.FilmWithCharacters
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilm
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilmCharacter
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomPlanet

@Dao
interface FilmCharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomFilmCharacter: RoomFilmCharacter)

    @Query("SELECT * FROM RoomFilmCharacter WHERE filmUrl = :url")
    fun findForFilmUrl(url: String): List<RoomFilmCharacter>

    @Query("SELECT * FROM RoomFilm")
    fun getFilmWithCharacters(): List<FilmWithCharacters>

}