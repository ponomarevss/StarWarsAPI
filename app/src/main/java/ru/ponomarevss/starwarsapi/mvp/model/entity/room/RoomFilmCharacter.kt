package ru.ponomarevss.starwarsapi.mvp.model.entity.room

import androidx.room.Entity

@Entity(
    primaryKeys = ["filmUrl", "characterUrl"]
)
data class RoomFilmCharacter(
    var filmUrl: String,
    var characterUrl: String
)