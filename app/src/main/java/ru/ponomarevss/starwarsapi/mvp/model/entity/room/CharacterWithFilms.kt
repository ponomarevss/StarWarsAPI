package ru.ponomarevss.starwarsapi.mvp.model.entity.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterWithFilms(
    @Embedded
    val roomCharacter: RoomCharacter,
    @Relation(
        parentColumn = "cId",
        entity = RoomFilm::class,
        entityColumn = "fId",
        associateBy = Junction(
            value = RoomFilmCharacter::class,
            parentColumn = "characterId",
            entityColumn = "filmId"
        )
    )
    val roomFilms: List<RoomFilm>)