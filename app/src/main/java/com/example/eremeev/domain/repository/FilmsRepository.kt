package com.example.eremeev.domain.repository

import com.example.eremeev.domain.entity.DetailedFilm
import com.example.eremeev.domain.entity.Film
import io.reactivex.Completable
import io.reactivex.Single

interface FilmsRepository {

    fun getTopFilms(): Single<List<Film>>

    fun getFavoriteFilms(): Single<List<Film>>

    fun getDetailedFilm(id: Int): Single<DetailedFilm>

    fun addToFavorite(film: Film): Completable

    fun deleteFromFavorite(id: Int): Completable
}