package ru.ponomarevss.starwarsapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomPlanet(
    @PrimaryKey var pUrl: String,
    var name: String,
    var diameter: String,
    var climate: String,
    var gravity: String,
    var terrain: String,
    var population: String
)