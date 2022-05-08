package ru.ponomarevss.starwarsapi.mvp.view.list

interface FilmItemView: IItemView {
    fun setTitle(text: String)
    fun setDirector(text: String)
    fun setProducer(text: String)
    fun setReleaseDate(text: String)
}