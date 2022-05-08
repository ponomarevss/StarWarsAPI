package ru.ponomarevss.starwarsapi.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.model.navigation.IScreens
import ru.ponomarevss.starwarsapi.ui.fragment.CharactersFragment
import ru.ponomarevss.starwarsapi.ui.fragment.FilmsFragment
import ru.ponomarevss.starwarsapi.ui.fragment.PlanetFragment

class AndroidScreens : IScreens {
    override fun films(): Screen  = FragmentScreen { FilmsFragment.newInstance() }

    override fun chars(film: Film): Screen  = FragmentScreen { CharactersFragment.newInstance(film) }

    override fun planet(homeworld: String): Screen  = FragmentScreen { PlanetFragment.newInstance(homeworld) }
}