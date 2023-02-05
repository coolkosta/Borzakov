package ru.coolkosta.cinematest.data.repositories.remote


import io.reactivex.rxjava3.core.Observable
import ru.coolkosta.cinematest.domain.models.CinemaListResponse
import ru.coolkosta.cinematest.domain.models.Film

interface FilmsRemoteDataSource {

    fun getMoviesRemote(): Observable<CinemaListResponse>

    fun getMovieForId(id: Int): Observable<Film>
}