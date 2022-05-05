package ru.ponomarevss.starwarsapi.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.ponomarevss.starwarsapi.mvp.model.entity.Character
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.RoomCharacter
import ru.ponomarevss.starwarsapi.mvp.model.entity.room.db.Database

class RoomCharacterCache(val db: Database): ICharacterCache {
    override fun putCharacter(character: Character): Completable = Completable.fromAction {
        with(character) {
            db.characterDao.insert(
                RoomCharacter(
                    cUrl = url,
                    name = name,
                    birthYear = birthYear,
                    gender = gender,
                    homeworld = homeworld
                )
            )
        }
    }

    override fun getCharacter(url: String): Single<Character> = Single.fromCallable {
        val roomCharacter = db.characterDao.findForUrl(url)
        roomCharacter?.let {
            Character(
                name = it.name,
                birthYear = it.birthYear,
                gender = it.gender,
                homeworld = it.homeworld,
                url = it.cUrl
            )
        }
    }
}