package com.example.eremeev.data.datasource.database.mapper

import com.example.eremeev.data.datasource.database.entity.FilmDbModel
import com.example.eremeev.domain.entity.Film
import org.junit.Test

internal class DomainToDatabaseFilmMapperKtTest {

    @Test
    fun checkMappedData() {
        //    Given
        val domainFilm = Film(
            id = 1,
            posterUrl = "http",
            name = "Чебурашка",
            genre = "Приключения (2023)",
            isFavorite = false,
        )

        //    When
        val result = mapDomainFilm(domainFilm)

        //    Then
        val expected = FilmDbModel(
            id = 1,
            posterUrl = "http",
            name = "Чебурашка",
            genre = "Приключения (2023)",
            isFavorite = false,
        )
        assert(result == expected)
    }
}