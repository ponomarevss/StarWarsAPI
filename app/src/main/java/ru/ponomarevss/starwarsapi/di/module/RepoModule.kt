package ru.ponomarevss.starwarsapi.di.module

import dagger.Module
import dagger.Provides
import ru.ponomarevss.starwarsapi.mvp.model.api.IDataSource
import ru.ponomarevss.starwarsapi.mvp.model.cache.ICharactersCache
import ru.ponomarevss.starwarsapi.mvp.model.cache.IFilmsCache
import ru.ponomarevss.starwarsapi.mvp.model.cache.IPlanetCache
import ru.ponomarevss.starwarsapi.mvp.model.network.INetworkStatus
import ru.ponomarevss.starwarsapi.mvp.model.repo.*
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun filmsRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IFilmsCache
    ): IFilmsRepo = RetrofitFilmsRepo(api, networkStatus, cache)

    @Provides
    @Singleton
    fun charactersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICharactersCache
    ): ICharactersRepo = RetrofitCharactersRepo(api, networkStatus, cache)

    @Provides
    @Singleton
    fun planetRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IPlanetCache
    ): IPlanetRepo = RetrofitPlanetRepo(api, networkStatus, cache)
}