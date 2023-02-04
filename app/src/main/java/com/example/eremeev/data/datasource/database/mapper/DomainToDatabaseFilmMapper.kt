package com.example.eremeev.data.datasource.database.mapper

import com.example.eremeev.data.datasource.database.entity.FilmDbModel
import com.example.eremeev.domain.entity.Film

fun mapDomainFilm(film: Film): FilmDbModel {
    return FilmDbModel(
        id = film.id,
        posterUrl = film.posterUrl,
        name = film.name,
        genre = film.genre,
        isFavorite = film.isFavorite,
    )
}