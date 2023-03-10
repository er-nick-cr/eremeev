package com.example.eremeev.data.datasource.network.entity.film

import com.google.gson.annotations.SerializedName

class NetworkFilm(
    @SerializedName("filmId")
    val filmId: Int,

    @SerializedName("nameRu")
    val nameRu: String,

    @SerializedName("nameEn")
    val nameEn: String,

    @SerializedName("year")
    val year: String,

    @SerializedName("filmLength")
    val filmLength: String,

    @SerializedName("countries")
    val countries: List<Country>,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("ratingVoteCount")
    val ratingVoteCount: Int,

    @SerializedName("posterUrl")
    val posterUrl: String,

    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String,

    @SerializedName("ratingChange")
    val ratingChange: Any,
)