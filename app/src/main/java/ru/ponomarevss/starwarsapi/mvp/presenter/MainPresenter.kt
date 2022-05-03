package ru.ponomarevss.starwarsapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.ponomarevss.starwarsapi.mvp.model.navigation.IScreens
import ru.ponomarevss.starwarsapi.mvp.view.MainView
import javax.inject.Inject

class MainPresenter: MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        router.replaceScreen(screens.seasons())
//        router.replaceScreen(screens.table())
    }

    fun backPressed() = router.exit()
}