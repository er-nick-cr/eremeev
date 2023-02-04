package com.example.eremeev.domain.repository

import com.example.eremeev.domain.entity.Film
import io.reactivex.Completable
import io.reactivex.Single

interface FilmsRepository {

    fun getTopFilms(): Single<List<Film>>

    fun addToFavorite(film: Film): Completable
}