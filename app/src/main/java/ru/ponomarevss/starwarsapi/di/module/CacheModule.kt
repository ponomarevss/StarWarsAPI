package ru.ponomarevss.starwarsapi.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.ponomarevss.starwarsapi.mvp.model.cache.*
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.db.Database
import ru.ponomarevss.starwarsapi.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java,
        Database.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun filmsCache(db: Database): IFilmsCache = RoomFilmsCache(db)

    @Singleton
    @Provides
    fun characterCache(db: Database): ICharactersCache = RoomCharactersCache(db)

    @Singleton
    @Provides
    fun planetCache(db: Database): IPlanetCache = RoomPlanetCache(db)
}