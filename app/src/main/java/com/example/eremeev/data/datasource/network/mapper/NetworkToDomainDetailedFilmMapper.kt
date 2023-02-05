package com.example.eremeev.data.datasource.network.mapper

import com.example.eremeev.data.datasource.network.entity.detailed_film.Country
import com.example.eremeev.data.datasource.network.entity.detailed_film.DetailedNetworkFilm
import com.example.eremeev.data.datasource.network.entity.detailed_film.Genre
import com.example.eremeev.domain.entity.DetailedFilm

fun mapNetworkDetailedFilm(data: DetailedNetworkFilm): DetailedFilm {
    return DetailedFilm(
        bannerUrl = data.posterUrl,
        title = data.nameRu,
        description = data.description,
        genres = getGenres(data.genres),
        countries = getCountries(data.countries),
    )
}

private fun getGenres(genresNetwork: List<Genre>): String {
    var genres = " "

    genresNetwork.map { genre ->
        genres += ", ${genre.genre}"
    }

    return genres.replaceFirst(", ", " ")
}

private fun getCountries(countriesNetwork: List<Country>): String {
    var countries = " "

    countriesNetwork.map { country ->
        countries += ", ${country.country}"
    }

    return countries.replaceFirst(", ", " ")
}