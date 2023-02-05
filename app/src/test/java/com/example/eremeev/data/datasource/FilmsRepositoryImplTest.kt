package com.example.eremeev.data.datasource

import com.example.eremeev.data.datasource.database.DatabaseDatasource
import com.example.eremeev.data.datasource.network.NetworkDatasource
import com.example.eremeev.data.repository.FilmsRepositoryImpl
import com.example.eremeev.domain.entity.Film
import io.reactivex.Single
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class FilmsRepositoryImplTest {

    @Test
    fun checkEnrichFavorites() {
//        Given
        val films = listOf(
            Film(
                id = 1,
                posterUrl = "",
                name = "",
                genre = "",
                isFavorite = false,
            ),
            Film(
                id = 2,
                posterUrl = "",
                name = "",
                genre = "",
                isFavorite = false,
            ),
        )
        val databaseFilms = listOf(
            Film(
                id = 2,
                posterUrl = "",
                name = "",
                genre = "",
                isFavorite = true,
            ),
        )
        val filmsRepositoryImpl = FilmsRepositoryImpl(
            networkDatasource = mock { on { getFilms() } doReturn Single.just(films) },
            databaseDatasource = mock { on { getFilms() } doReturn Single.just(databaseFilms) },
        )

//        When
        val result = filmsRepositoryImpl.getTopFilms()
            .blockingGet()

//        Then
        val expected = listOf(
            Film(
                id = 1,
                posterUrl = "",
                name = "",
                genre = "",
                isFavorite = false,
            ),
            Film(
                id = 2,
                posterUrl = "",
                name = "",
                genre = "",
                isFavorite = true,
            ),
        )
        assert(result == expected)
    }

    @Test
    fun checkDatabaseAndNetworkInvokedBoth() {
//        Given
        val networkDatasource: NetworkDatasource = mock { on { getFilms() } doReturn Single.just(listOf()) }
        val databaseDatasource: DatabaseDatasource = mock { on { getFilms() } doReturn Single.just(listOf()) }

        val filmsRepositoryImpl = FilmsRepositoryImpl(
            networkDatasource = networkDatasource,
            databaseDatasource = databaseDatasource,
        )

//        When
        filmsRepositoryImpl.getTopFilms()
            .blockingGet()
//        Then
        verify(networkDatasource).getFilms()
        verify(databaseDatasource).getFilms()
    }
}