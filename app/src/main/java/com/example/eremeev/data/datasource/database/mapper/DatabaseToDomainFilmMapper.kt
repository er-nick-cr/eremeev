package com.example.eremeev.data.datasource.database.mapper

import com.example.eremeev.data.datasource.database.entity.FilmDbModel
import com.example.eremeev.domain.entity.Film

fun mapDatabaseFilms(films: List<FilmDbModel>): List<Film> {
    return films.map { film ->
        Film(
            id = film.id,
            posterUrl = film.posterUrl,
            name = film.name,
            genre = film.genre,
            isFavorite = film.isFavorite,
        )
    }
}