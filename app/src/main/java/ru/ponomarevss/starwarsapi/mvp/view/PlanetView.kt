package ru.ponomarevss.starwarsapi.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface PlanetView: MvpView {
    fun setDiameter(text: String)
    fun setClimate(text: String)
    fun setGravity(text: String)
    fun setTerrain(text: String)
    fun setPopulation(text: String)
    fun setTitle(text: String)
    fun setHomeButton()
    fun setAlert(text: String)
}