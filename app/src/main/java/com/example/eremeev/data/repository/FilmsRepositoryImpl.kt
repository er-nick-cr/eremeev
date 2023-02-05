package com.example.eremeev.data.repository

import com.example.eremeev.data.datasource.database.DatabaseDatasource
import com.example.eremeev.data.datasource.network.NetworkDatasource
import com.example.eremeev.domain.entity.DetailedFilm
import com.example.eremeev.domain.entity.Film
import com.example.eremeev.domain.repository.FilmsRepository
import io.reactivex.Completable
import io.reactivex.Single

class FilmsRepositoryImpl(
    private val networkDatasource: NetworkDatasource,
    private val databaseDatasource: DatabaseDatasource,
) : FilmsRepository {

    override fun getTopFilms(): Single<List<Film>> {
        return databaseDatasource.getFilms()
            .flatMap { savedFilms ->
                networkDatasource.getFilms().map { films ->
                    setIsFavorite(savedFilms, films)
                }
            }
    }

    override fun getFavoriteFilms(): Single<List<Film>> {
        return databaseDatasource.getFilms()
    }

    override fun getDetailedFilm(id: Int): Single<DetailedFilm> {
        return networkDatasource.getDetailedFilm(id)
    }

    override fun addToFavorite(film: Film): Completable {
        return databaseDatasource.addToFavorite(film)
    }

    override fun deleteFromFavorite(id: Int): Completable {
        return databaseDatasource.deleteFromFavorite(id)
    }

    private fun setIsFavorite(savedFilms: List<Film>, films: List<Film>): List<Film> {
        val favoriteIds = savedFilms.map { it.id }

        return films.map { film ->
            if (favoriteIds.contains(film.id)) {
                film.copy(isFavorite = true)
            } else {
                film
            }
        }
    }
}