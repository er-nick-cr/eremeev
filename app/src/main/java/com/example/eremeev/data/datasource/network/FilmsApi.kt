package com.example.eremeev.data.datasource.network

import com.example.eremeev.data.datasource.network.entity.detailed_film.DetailedNetworkFilm
import com.example.eremeev.data.datasource.network.entity.film.NetworkFilm
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApi {

    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    fun getFilms() : Single<NetworkFilm>

    @GET("api/v2.2/films/{id}")
    fun getDetailedFilm(@Path("id") id: Int) : Single<DetailedNetworkFilm>
}