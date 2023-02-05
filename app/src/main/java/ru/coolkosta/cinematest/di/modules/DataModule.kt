package ru.coolkosta.cinematest.di.modules

import dagger.Module
import dagger.Provides
import ru.coolkosta.cinematest.data.api.CinemaApi
import ru.coolkosta.cinematest.domain.repository.FilmsRepository
import ru.coolkosta.cinematest.data.repositories.FilmsRepositoryImpl
import ru.coolkosta.cinematest.data.repositories.remote.FilmsRemoteDataSource
import ru.coolkosta.cinematest.data.repositories.remote.retrofit.RetrofitMoviesDataSource
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesRemoteDataSource(cinemaApi: CinemaApi): FilmsRemoteDataSource =
        RetrofitMoviesDataSource(cinemaApi = cinemaApi)

    @Provides
    @Singleton
    fun providesFilmsRepository(
        remote: FilmsRemoteDataSource
    ): FilmsRepository = FilmsRepositoryImpl(remote)


}