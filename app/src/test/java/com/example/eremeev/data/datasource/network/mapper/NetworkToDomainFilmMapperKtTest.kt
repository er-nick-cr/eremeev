package com.example.eremeev.data.datasource.network.mapper

import com.example.eremeev.data.datasource.network.entity.film.Country
import com.example.eremeev.data.datasource.network.entity.film.Genre
import com.example.eremeev.data.datasource.network.entity.film.NetworkFilm
import com.example.eremeev.domain.entity.Film
import org.junit.Test

internal class NetworkToDomainFilmMapperKtTest {

    @Test
    fun checkMappedData() {
//        Given
        val networkFilm = NetworkFilm(
            filmId = 1,
            nameRu = "Чебурашка",
            nameEn = "Cheburashka",
            year = "2023",
            filmLength = "1:23",
            countries = listOf(Country("Россия")),
            genres = listOf(Genre("приключения")),
            rating = "8",
            ratingVoteCount = 8,
            posterUrl = "http",
            posterUrlPreview = "http",
            ratingChange = 5,
        )

//        When
        val result = mapNetworkFilm(networkFilm)

//        Then
        val expected = Film(
            id = 1,
            posterUrl = "http",
            name = "Чебурашка",
            genre = "Приключения (2023)",
            isFavorite = false,
        )
        assert(result == expected)
    }
}