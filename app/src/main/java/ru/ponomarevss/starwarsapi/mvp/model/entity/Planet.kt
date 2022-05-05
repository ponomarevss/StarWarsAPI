package ru.ponomarevss.starwarsapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    @Expose val name: String,
    @Expose val diameter: String,
    @Expose val climate: String,
    @Expose val gravity: String,
    @Expose val terrain: String,
    @Expose val population: String,
    @Expose val url: String
): Parcelable
