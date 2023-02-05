package com.example.eremeev.data.datasource.database

import com.example.eremeev.data.datasource.database.mapper.mapDatabaseFilm
import com.example.eremeev.data.datasource.database.mapper.mapDomainFilm
import com.example.eremeev.domain.entity.Film
import io.reactivex.Completable
import io.reactivex.Single

interface DatabaseDatasource {
    fun addToFavorite(film: Film): Completable
    fun deleteFromFavorite(id: Int): Completable
    fun getFilms(): Single<List<Film>>
}

class DatabaseDatasourceImpl(
    private val filmsDao: FilmsDao,
) : DatabaseDatasource {

    override fun addToFavorite(film: Film): Completable {
        return filmsDao.addToFavorite(mapDomainFilm(film))
    }

    override fun deleteFromFavorite(id: Int): Completable {
        return filmsDao.deleteFromFavorite(id)
    }

    override fun getFilms(): Single<List<Film>> {
        return filmsDao.getFilms()
            .map { films ->
                films.map { film -> mapDatabaseFilm(film) }
            }
    }
}