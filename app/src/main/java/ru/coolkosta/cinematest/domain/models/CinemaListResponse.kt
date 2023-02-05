package ru.coolkosta.cinematest.domain.models

import com.google.gson.annotations.SerializedName

data class CinemaListResponse(
    @SerializedName("films")
    var films: ArrayList<TopFilmsResponse>,

    @SerializedName("pagesCount")
    var pagesCount: Int
)

data class TopFilmsResponse(
    @SerializedName("filmId")
    var id: Int,

    @SerializedName("nameRu")
    var title: String,

    @SerializedName("year")
    var year: String,

    @SerializedName("genres")
    var genres: ArrayList<Genre>,

    @SerializedName("posterUrl")
    var poster: String
)

data class Genre(
    @SerializedName("genre")
    var genre: String
)