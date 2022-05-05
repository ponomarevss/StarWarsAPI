package ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomCharacter
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilm

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomCharacter: RoomCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomCharacters: RoomCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomCharacters: List<RoomCharacter>)

    @Update
    fun update(roomCharacter: RoomCharacter)

    @Update
    fun update(vararg roomCharacters: RoomCharacter)

    @Update
    fun update(roomCharacters: List<RoomCharacter>)

    @Delete
    fun delete(roomCharacter: RoomCharacter)

    @Delete
    fun delete(vararg roomCharacters: RoomCharacter)

    @Delete
    fun delete(roomCharacters: List<RoomCharacter>)

    @Query("SELECT * FROM RoomCharacter WHERE cUrl = :url LIMIT 1")
    fun findForUrl(url: String): RoomCharacter?
}