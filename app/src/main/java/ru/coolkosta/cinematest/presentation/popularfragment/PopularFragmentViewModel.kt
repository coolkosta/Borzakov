package ru.coolkosta.cinematest.presentation.popularfragment

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
import ru.coolkosta.cinematest.domain.models.TopFilmsResponse
import ru.coolkosta.cinematest.domain.repository.FilmsRepository

class PopularFragmentViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val moviesRepository: FilmsRepository
) : ViewModel() {

    private val _cinemaListState = MutableLiveData<List<TopFilmsResponse>>()
    val cinemaListState: LiveData<List<TopFilmsResponse>>
        get() = _cinemaListState
    private val compositeDisposable = CompositeDisposable()

    private val _emptyList = MutableLiveData<List<TopFilmsResponse>>()
    val emptyList: LiveData<List<TopFilmsResponse>>
        get() = _emptyList

    init {

        getApi()
    }

    fun tryLoadAgain() {
        getApi()
    }

    private fun getApi() {
        compositeDisposable.add(
            moviesRepository.getFilmsRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _cinemaListState.value = it.films
                }, {
                    _emptyList.value = emptyList()
                    _cinemaListState.value = emptyList()
                })
        )
    }


    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): PopularFragmentViewModel
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}