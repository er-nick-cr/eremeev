package com.example.eremeev.data.datasource.database

import com.example.eremeev.data.datasource.database.mapper.mapDatabaseFilms
import com.example.eremeev.data.datasource.database.mapper.mapDomainFilm
import com.example.eremeev.domain.entity.Film
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseDatasource(
    private val filmsDao: FilmsDao,
) {

    fun addToFavorite(film: Film): Completable {
        return filmsDao.addToFavorite(mapDomainFilm(film))
    }

    fun deleteFromFavorite(id: Int): Completable {
        return filmsDao.deleteFromFavorite(id)
    }

    fun getFilms(): Single<List<Film>> {
        return filmsDao.getFilms()
            .map(::mapDatabaseFilms)
    }
}