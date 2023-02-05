package com.example.eremeev.data.datasource.network.mapper

import com.example.eremeev.data.datasource.network.entity.detailed_film.Country
import com.example.eremeev.data.datasource.network.entity.detailed_film.DetailedNetworkFilm
import com.example.eremeev.data.datasource.network.entity.detailed_film.Genre
import com.example.eremeev.domain.entity.DetailedFilm
import org.junit.Test

internal class NetworkToDomainDetailedFilmMapperKtTest {

    @Test
    fun checkMappedData() {
//        Given
        val networkDetailedFilm = DetailedNetworkFilm(
            kinopoiskId = 1,
            imdbId = 1,
            nameRu = "Чебурашка",
            nameEn = "Cheburashka",
            nameOriginal = "Чебурашка",
            posterUrl = "http",
            posterUrlPreview = "http",
            coverUrl = "http",
            logoUrl = "http",
            reviewsCount = 1,
            ratingGoodReview = 1,
            ratingGoodReviewVoteCount = 1,
            ratingKinopoisk = 8.5,
            ratingKinopoiskVoteCount = 8,
            ratingImdb = 8.5,
            ratingImdbVoteCount = 1,
            ratingFilmCritics = 2.2,
            ratingFilmCriticsVoteCount = 1,
            ratingAwait = 1.1,
            ratingAwaitCount = 1,
            ratingRfCritics = 8.5,
            ratingRfCriticsVoteCount = 8,
            webUrl = "http",
            year = 2023,
            filmLength = 1,
            slogan = "",
            description = "Иногда, чтобы вернуть солнце",
            shortDescription = "Иногда, чтобы вернуть солнце",
            editorAnnotation = 5,
            isTicketsAvailable = true,
            productionStatus = 5,
            type = "",
            ratingMpaa = "",
            ratingAgeLimits = "",
            countries = listOf(Country("Россия")),
            genres = listOf(Genre("комедия")),
            startYear = 2023,
            endYear = 2023,
            serial = false,
            shortFilm = false,
            completed = true,
            hasImax = false,
            has3D = false,
            lastSync = "",
        )

//        When
        val result = mapNetworkDetailedFilm(networkDetailedFilm)

//        Then
        val expected = DetailedFilm(
            bannerUrl = "http",
            title = "Чебурашка",
            description = "Иногда, чтобы вернуть солнце",
            genres = " комедия",
            countries = " Россия",
        )
        assert(result.countries == expected.countries)
    }
}