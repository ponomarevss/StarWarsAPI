package ru.ponomarevss.starwarsapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    @Expose val title: String,
    @Expose val episodeId: Int,
    @Expose val director: String,
    @Expose val producer: String,
    @Expose val releaseDate: String,
    @Expose val characters: List<String>,
    @Expose val url: String
): Parcelable
