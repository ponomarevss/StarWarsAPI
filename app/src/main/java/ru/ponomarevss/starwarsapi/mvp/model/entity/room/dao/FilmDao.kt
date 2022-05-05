package ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilm

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomFilm: RoomFilm)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomFilms: RoomFilm)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomFilms: List<RoomFilm>)

    @Update
    fun update(roomFilm: RoomFilm)

    @Update
    fun update(vararg roomFilms: RoomFilm)

    @Update
    fun update(roomFilms: List<RoomFilm>)

    @Delete
    fun delete(roomFilm: RoomFilm)

    @Delete
    fun delete(vararg roomFilms: RoomFilm)

    @Delete
    fun delete(roomFilms: List<RoomFilm>)

    @Query("SELECT * FROM RoomFilm")
    fun getAll(): List<RoomFilm>

    @Query("SELECT * FROM RoomFilm WHERE fUrl = :url LIMIT 1")
    fun findForUrl(url: String): RoomFilm?
}