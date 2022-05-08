package ru.ponomarevss.starwarsapi.mvp.view.list

interface CharacterItemView: IItemView {
    fun setName(text: String)
    fun setGender(text: String)
    fun setBirthYear(text: String)
}