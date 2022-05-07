package ru.ponomarevss.starwarsapi.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Character

interface ICharactersCache {
    fun putCharacter(character: Character): Completable
    fun getCharacter(url: String): Single<Character>
}