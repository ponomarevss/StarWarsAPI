package ru.ponomarevss.starwarsapi.di.module

import dagger.Module
import dagger.Provides
import ru.ponomarevss.starwarsapi.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}