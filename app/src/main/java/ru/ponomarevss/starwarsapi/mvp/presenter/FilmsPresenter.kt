package ru.ponomarevss.starwarsapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.ponomarevss.starwarsapi.BuildConfig.TITLE
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.model.navigation.IScreens
import ru.ponomarevss.starwarsapi.mvp.model.repo.IFilmsRepo
import ru.ponomarevss.starwarsapi.mvp.presenter.list.IFilmsListPresenter
import ru.ponomarevss.starwarsapi.mvp.view.FilmsView
import ru.ponomarevss.starwarsapi.mvp.view.list.FilmItemView
import javax.inject.Inject

class FilmsPresenter(private val uiScheduler: Scheduler) : MvpPresenter<FilmsView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var repo: IFilmsRepo

    inner class FilmsListPresenter : IFilmsListPresenter {
        val films = mutableListOf<Film>()
        override var itemClickListener: ((FilmItemView) -> Unit)? = null

        override fun bindView(view: FilmItemView) {
            val film = films[view.pos]
            with(view) {
                with(film) {
                    setTitle(title)
                    setDirector(director)
                    setProducer(producer)
                    setReleaseDate(releaseDate)
                }
            }
        }

        override fun getCount(): Int = films.size
    }

    val filmsListPresenter = FilmsListPresenter()
    private val initialFilmsList = mutableListOf<Film>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        viewState.setTitle(TITLE)
        viewState.setHomeButton()

        filmsListPresenter.itemClickListener = {
            val film = filmsListPresenter.films[it.pos]
            router.navigateTo(screens.chars(film))
        }
    }

    private fun loadData() {
        try {
            repo.getFilms().observeOn(uiScheduler)
                .subscribe({
                    initialFilmsList.clear()
                    initialFilmsList.addAll(it.sortedBy { film -> film.episodeId })
                    setFilmsListPresenter()
                },{
                    viewState.setAlert(it.message.toString())
                })
        } catch (e: Throwable) {
            viewState.setAlert(e.message.toString())
        }
    }

    private fun setFilmsListPresenter() {
        filmsListPresenter.films.clear()
        filmsListPresenter.films.addAll(initialFilmsList)
        viewState.update()
    }

    fun filterFilms(filter: String?) {
        filmsListPresenter.films.clear()
        filter?.let {
            initialFilmsList.map { film ->
                if (film.title.contains(it, true)) {
                    filmsListPresenter.films.add(film)
                }
            }
        }
        viewState.update()
    }
    
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}