package ru.ponomarevss.starwarsapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCharacter(
    @PrimaryKey var cUrl: String,
    var name: String,
    var birthYear: String,
    var gender: String,
    var homeworld: String,
)