package com.example.eremeev.data.datasource.database.mapper

import com.example.eremeev.data.datasource.database.entity.FilmDbModel
import com.example.eremeev.domain.entity.Film
import org.junit.Test

internal class DatabaseToDomainFilmMapperKtTest {

    @Test
    fun checkMappedData() {
        //    Given
        val databaseFilm = FilmDbModel(
            id = 1,
            posterUrl = "http",
            name = "Чебурашка",
            genre = "Приключения (2023)",
            isFavorite = true,
        )

        //    When
        val result = mapDatabaseFilm(databaseFilm)

        //    Then
        val expected = Film(
            id = 1,
            posterUrl = "http",
            name = "Чебурашка",
            genre = "Приключения (2023)",
            isFavorite = true,
        )
        assert(result == expected)
    }
}