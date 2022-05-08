package ru.ponomarevss.starwarsapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.ponomarevss.starwarsapi.mvp.model.entity.Character
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.model.navigation.IScreens
import ru.ponomarevss.starwarsapi.mvp.model.repo.ICharactersRepo
import ru.ponomarevss.starwarsapi.mvp.presenter.list.ICharactersListPresenter
import ru.ponomarevss.starwarsapi.mvp.view.CharactersView
import ru.ponomarevss.starwarsapi.mvp.view.list.CharacterItemView
import javax.inject.Inject

class CharactersPresenter(private val uiScheduler: Scheduler, val film: Film) : MvpPresenter<CharactersView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var repo: ICharactersRepo

    inner class CharactersListPresenter : ICharactersListPresenter {
        val characters = mutableListOf<Character>()
        override var itemClickListener: ((CharacterItemView) -> Unit)? = null

        override fun bindView(view: CharacterItemView) {
            val character = characters[view.pos]
            with(view) {
                with(character) {
                    setName(name)
                    setGender(gender)
                    setBirthYear(birthYear)
                }
            }
        }

        override fun getCount(): Int = characters.size
    }

    val charactersListPresenter = CharactersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        viewState.setTitle(film.title)
        viewState.setHomeButton()

        charactersListPresenter.itemClickListener = {
            val character = charactersListPresenter.characters[it.pos]
            router.navigateTo(screens.planet(character.homeworld))
        }
    }

    private fun loadData() {
        try {
            repo.getCharacters(film.characters).observeOn(uiScheduler)
                .subscribe({
                    charactersListPresenter.characters.clear()
                    charactersListPresenter.characters.addAll(it)
                    viewState.update()
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