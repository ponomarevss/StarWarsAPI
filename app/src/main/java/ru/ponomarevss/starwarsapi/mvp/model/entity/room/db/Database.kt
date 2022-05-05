package ru.ponomarevss.starwarsapi.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomCharacter
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilm
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomFilmCharacter
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomPlanet
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao.CharacterDao
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao.FilmCharacterDao
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao.FilmDao
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.dao.PlanetDao

@androidx.room.Database(
    entities = [
        RoomFilm::class,
        RoomCharacter::class,
        RoomPlanet::class,
        RoomFilmCharacter::class
               ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val filmDao: FilmDao
    abstract val characterDao: CharacterDao
    abstract val planetDao: PlanetDao
    abstract val filmCharacterDao: FilmCharacterDao

    companion object {
        const val DB_NAME = "database.db"
    }
}
