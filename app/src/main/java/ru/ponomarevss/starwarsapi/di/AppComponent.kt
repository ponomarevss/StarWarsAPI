package ru.ponomarevss.starwarsapi.di

import dagger.Component
import ru.ponomarevss.starwarsapi.di.module.AppModule
import ru.ponomarevss.starwarsapi.di.module.CiceroneModule
import ru.ponomarevss.starwarsapi.mvp.presenter.MainPresenter
import ru.ponomarevss.starwarsapi.ui.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
//        ApiModule::class,
        AppModule::class,
//        CacheModule::class,
        CiceroneModule::class,
//        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}