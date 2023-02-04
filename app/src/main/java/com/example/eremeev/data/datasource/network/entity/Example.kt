package com.example.eremeev.data.datasource.network.entity

import com.example.Film
import com.google.gson.annotations.SerializedName

class Example(
    @SerializedName("pagesCount")
    var pagesCount: Int,
    @SerializedName("films")
    var films: List<Film>,
)