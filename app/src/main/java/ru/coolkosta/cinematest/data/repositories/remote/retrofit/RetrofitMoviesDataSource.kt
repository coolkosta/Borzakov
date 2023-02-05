package ru.coolkosta.cinematest.data.repositories.remote.retrofit


import io.reactivex.rxjava3.core.Observable
import ru.coolkosta.cinematest.data.api.CinemaApi
import ru.coolkosta.cinematest.domain.models.CinemaListResponse
import ru.coolkosta.cinematest.domain.models.Film
import ru.coolkosta.cinematest.data.repositories.remote.FilmsRemoteDataSource
import ru.coolkosta.cinematest.util.TYPE

class RetrofitMoviesDataSource(private val cinemaApi: CinemaApi) : FilmsRemoteDataSource {

    override fun getMoviesRemote(): Observable<CinemaListResponse> =
        cinemaApi.getPopularMoviesList(TYPE)


    override fun getMovieForId(id: Int): Observable<Film> =
        cinemaApi.getMovieDetails(id)
}