package ru.coolkosta.cinematest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.coolkosta.cinematest.di.modules.DataModule
import ru.coolkosta.cinematest.di.modules.RemoteModule
import ru.coolkosta.cinematest.presentation.moviedetailfragment.MovieDetailFragment
import ru.coolkosta.cinematest.presentation.moviedetailfragment.MovieDetailFragmentViewModel
import ru.coolkosta.cinematest.presentation.popularfragment.PopularFragment
import ru.coolkosta.cinematest.presentation.popularfragment.PopularFragmentViewModel
import javax.inject.Singleton

@Component(modules = [RemoteModule::class, DataModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: PopularFragment)
    fun inject(fragment: MovieDetailFragment)
    fun injectMoviesListViewModel(): PopularFragmentViewModel.Factory
    fun injectMovieDetailsViewModel(): MovieDetailFragmentViewModel.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}