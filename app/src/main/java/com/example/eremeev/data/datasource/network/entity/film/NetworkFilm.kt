package com.example.eremeev.data.datasource.network.entity.film

import com.google.gson.annotations.SerializedName

class NetworkFilm(
    @SerializedName("pagesCount")
    var pagesCount: Int,
    @SerializedName("films")
    var films: List<Film>,
)