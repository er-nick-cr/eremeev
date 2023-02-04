package com.example.eremeev.data.datasource.network.mapper

import com.example.eremeev.data.datasource.network.entity.Example
import com.example.eremeev.domain.entity.Film

fun mapNetworkFilms(data: Example): List<Film> {
    val films = data.films

    return films.map { film ->
        Film(
            id = film.filmId,
            posterUrl = film.posterUrl,
            name = film.nameRu,
            genre = film.genres.first().genre.capitalize() + " (${film.year})",
            isFavorite = false,
        )
    }
}