package com.example.eremeev.data.datasource.network

import com.example.eremeev.data.datasource.network.mapper.mapNetworkDetailedFilm
import com.example.eremeev.data.datasource.network.mapper.mapNetworkFilm
import com.example.eremeev.domain.entity.DetailedFilm
import com.example.eremeev.domain.entity.Film
import io.reactivex.Single

interface NetworkDatasource {
    fun getFilms(): Single<List<Film>>
    fun getDetailedFilm(id: Int): Single<DetailedFilm>
}

class NetworkDatasourceImpl(
    private val filmsApi: FilmsApi,
) : NetworkDatasource {

    override fun getFilms(): Single<List<Film>> {
        return filmsApi.getFilms().map { networkFilms ->
            networkFilms.networkFilms
                .map { networkFilm -> mapNetworkFilm(networkFilm) }
        }
    }

    override fun getDetailedFilm(id: Int): Single<DetailedFilm> {
        return filmsApi.getDetailedFilm(id).map(::mapNetworkDetailedFilm)
    }
}