package ru.ponomarevss.starwarsapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @Expose val name: String,
    @Expose val birthYear: String,
    @Expose val gender: String,
    @Expose val homeworld: String,
    @Expose val url: String
): Parcelable