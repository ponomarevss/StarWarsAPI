package ru.ponomarevss.starwarsapi.mvp.model.entity.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FilmWithCharacters(
    @Embedded
    val roomFilm: RoomFilm,
    @Relation(
        parentColumn = "fId",
        entity = RoomCharacter::class,
        entityColumn = "cId",
        associateBy = Junction(
            value = RoomFilmCharacter::class,
            parentColumn = "filmId",
            entityColumn = "characterId"
        )
    )
    val roomCharacters: List<RoomCharacter>)