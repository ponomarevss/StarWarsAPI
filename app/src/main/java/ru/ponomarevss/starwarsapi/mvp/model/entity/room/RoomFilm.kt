package ru.ponomarevss.starwarsapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomFilm(
    @PrimaryKey var fUrl: String,
    var title: String,
    var episodeId: Int,
    var director: String,
    var producer: String,
    var releaseDate: String
)