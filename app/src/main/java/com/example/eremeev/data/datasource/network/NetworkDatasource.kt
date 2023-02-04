package com.example.eremeev.data.datasource.network

import com.example.eremeev.data.datasource.network.mapper.mapNetworkFilms
import com.example.eremeev.domain.entity.Film
import io.reactivex.Single

class NetworkDatasource(
    private val filmsApi: FilmsApi,
) {

    fun getFilms(): Single<List<Film>> {
        return filmsApi.getFilms().map(::mapNetworkFilms)
    }
}