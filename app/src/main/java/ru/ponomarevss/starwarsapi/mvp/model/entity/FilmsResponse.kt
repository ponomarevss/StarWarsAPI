package ru.ponomarevss.starwarsapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmsResponse(
    @Expose val results: List<Film>
): Parcelable
