package ru.ponomarevss.starwarsapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.ponomarevss.starwarsapi.mvp.model.entity.Planet
import ru.ponomarevss.starwarsapi.mvp.model.navigation.IScreens
import ru.ponomarevss.starwarsapi.mvp.model.repo.IPlanetRepo
import ru.ponomarevss.starwarsapi.mvp.view.PlanetView
import javax.inject.Inject

class PlanetPresenter(private val uiScheduler: Scheduler, val homeworld: String) : MvpPresenter<PlanetView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var repo: IPlanetRepo

    var planet: Planet? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
        viewState.setHomeButton()
    }

    private fun loadData() {
        try {
            repo.getPlanet(homeworld).observeOn(uiScheduler)
                .subscribe({
                    viewState.setTitle(it.name)
                    viewState.setDiameter(it.diameter)
                    viewState.setClimate(it.climate)
                    viewState.setGravity(it.gravity)
                    viewState.setTerrain(it.terrain)
                    viewState.setPopulation(it.population)
                },{
                    viewState.setAlert(it.message.toString())
                })
        } catch (e: Throwable) {
            viewState.setAlert(e.message.toString())
        }
    }
    
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}