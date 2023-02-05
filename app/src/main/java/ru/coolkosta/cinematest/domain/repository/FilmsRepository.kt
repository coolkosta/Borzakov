package ru.coolkosta.cinematest.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.coolkosta.cinematest.domain.models.CinemaListResponse
import ru.coolkosta.cinematest.domain.models.Film

interface FilmsRepository {

    fun getFilmsRemote(): Observable<CinemaListResponse>

    fun getFilmById(id: Int): Observable<Film>
}