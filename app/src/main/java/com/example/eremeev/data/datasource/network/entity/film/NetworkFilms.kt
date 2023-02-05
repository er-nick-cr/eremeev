package com.example.eremeev.data.datasource.network.entity.film

import com.google.gson.annotations.SerializedName

class NetworkFilms(
    @SerializedName("pagesCount")
    var pagesCount: Int,
    @SerializedName("films")
    var networkFilms: List<NetworkFilm>,
)