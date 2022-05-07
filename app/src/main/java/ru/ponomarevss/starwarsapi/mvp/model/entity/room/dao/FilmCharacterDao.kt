package ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilmCharacter

@Dao
interface FilmCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomFilmCharacter: RoomFilmCharacter)

    @Query("SELECT * FROM RoomFilmCharacter WHERE filmUrl = :url")
    fun findForFilmUrl(url: String): List<RoomFilmCharacter>
}