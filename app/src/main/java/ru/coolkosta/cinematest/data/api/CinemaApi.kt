package ru.coolkosta.cinematest.data.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.coolkosta.cinematest.domain.models.CinemaListResponse
import ru.coolkosta.cinematest.domain.models.Film
import ru.coolkosta.cinematest.util.TOKEN

interface CinemaApi {

    @GET("/api/v2.2/films/top")
    @Headers("X-API-KEY: $TOKEN")
    fun getPopularMoviesList(@Query("type") type: String): Observable<CinemaListResponse>

    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: $TOKEN")
    fun getMovieDetails(@Path("id") id: Int): Observable<Film>
}