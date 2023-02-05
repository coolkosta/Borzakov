package ru.coolkosta.cinematest.presentation.moviedetailfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.coolkosta.cinematest.domain.models.Film
import ru.coolkosta.cinematest.domain.repository.FilmsRepository

class MovieDetailFragmentViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle, private val moviesRepository: FilmsRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _cinemaState = MutableLiveData<Film>()
    val cinemaState: LiveData<Film>
        get() = _cinemaState

    fun getFilmById(id: Int) {
        compositeDisposable.add(
            moviesRepository.getFilmById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _cinemaState.value = it
                }, {

                })
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): MovieDetailFragmentViewModel
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}