package ru.ponomarevss.starwarsapi.ui

import android.app.Application
import ru.ponomarevss.starwarsapi.di.AppComponent
import ru.ponomarevss.starwarsapi.di.DaggerAppComponent
import ru.ponomarevss.starwarsapi.di.module.AppModule

class App : Application() {
    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}