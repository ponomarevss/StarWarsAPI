package ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomPlanet

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomPlanet: RoomPlanet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomPlanets: RoomPlanet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomPlanets: List<RoomPlanet>)

    @Update
    fun update(roomPlanet: RoomPlanet)

    @Update
    fun update(vararg roomPlanets: RoomPlanet)

    @Update
    fun update(roomPlanets: List<RoomPlanet>)

    @Delete
    fun delete(roomPlanet: RoomPlanet)

    @Delete
    fun delete(vararg roomPlanets: RoomPlanet)

    @Delete
    fun delete(roomPlanets: List<RoomPlanet>)

    @Query("SELECT * FROM RoomPlanet WHERE pUrl = :url LIMIT 1")
    fun findForUrl(url: String): RoomPlanet?
}