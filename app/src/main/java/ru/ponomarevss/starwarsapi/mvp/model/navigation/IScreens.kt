package ru.ponomarevss.starwarsapi.mvp.model.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun films(): Screen
    fun chars(filmUrl: String): Screen
    fun planet(planetUrl: String): Screen
}