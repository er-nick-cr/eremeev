package com.example.eremeev.data.datasource.network.mapper

import com.example.eremeev.data.datasource.network.entity.film.NetworkFilm
import com.example.eremeev.domain.entity.Film

fun mapNetworkFilm(film: NetworkFilm): Film {
    return Film(
        id = film.filmId,
        posterUrl = film.posterUrl,
        name = film.nameRu,
        genre = film.genres.first().genre.capitalize() + " (${film.year})",
        isFavorite = false,
    )
}