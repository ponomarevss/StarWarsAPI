package ru.ponomarevss.starwarsapi.mvp.model.navigation

import com.github.terrakok.cicerone.Screen
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film

interface IScreens {
    fun films(): Screen
    fun chars(film: Film): Screen
    fun planet(homeworld: String): Screen
}