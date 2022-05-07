package ru.ponomarevss.starwarsapi.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Character

interface ICharactersRepo {
    fun getCharacters(characters: List<String>): Single<List<Character>>
}