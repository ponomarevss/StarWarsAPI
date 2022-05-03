package ru.ponomarevss.starwarsapi.ui.navigation

import com.github.terrakok.cicerone.Screen
import ru.ponomarevss.starwarsapi.mvp.model.navigation.IScreens

class AndroidScreens : IScreens {
    override fun films(): Screen {
        TODO("Not yet implemented")
    }

    override fun chars(filmUrl: String): Screen {
        TODO("Not yet implemented")
    }

    override fun planet(planetUrl: String): Screen {
        TODO("Not yet implemented")
    }
}