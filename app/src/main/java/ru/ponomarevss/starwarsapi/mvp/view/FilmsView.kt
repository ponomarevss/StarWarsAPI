package ru.ponomarevss.starwarsapi.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FilmsView: MvpView {
    fun init()
    fun update()
    fun setTitle(text: String)
    fun setHomeButton()
    fun setAlert(text: String)
}