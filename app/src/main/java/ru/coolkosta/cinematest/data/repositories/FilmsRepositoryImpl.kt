package ru.coolkosta.cinematest.data.repositories

import io.reactivex.rxjava3.core.Observable
import ru.coolkosta.cinematest.domain.models.CinemaListResponse
import ru.coolkosta.cinematest.domain.models.Film
import ru.coolkosta.cinematest.data.repositories.remote.FilmsRemoteDataSource
import ru.coolkosta.cinematest.domain.repository.FilmsRepository

class FilmsRepositoryImpl(
    private val moviesRemoteDataSource: FilmsRemoteDataSource
) : FilmsRepository {

    override fun getFilmsRemote(): Observable<CinemaListResponse> =
        moviesRemoteDataSource.getMoviesRemote()

    override fun getFilmById(id: Int): Observable<Film> = moviesRemoteDataSource.getMovieForId(id)
}

