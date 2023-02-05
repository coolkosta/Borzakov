package ru.coolkosta.cinematest.domain.models

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("countries")
    val countries: List<Country>,

    @SerializedName("kinopoiskFilmId")
    val filmId: Int,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("nameRu")
    val title: String,

    @SerializedName("posterUrl")
    val posterUrl: String,

    @SerializedName("description")
    var description: String,

    )

data class Country(
    @SerializedName("country")
    var country: String
)