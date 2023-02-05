package com.example.eremeev.data.datasource.network.entity.detailed_film

import com.google.gson.annotations.SerializedName

class DetailedNetworkFilm (
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int?,

    @SerializedName("imdbId")
    val imdbId: Any?,

    @SerializedName("nameRu")
    val nameRu: String,

    @SerializedName("nameEn")
    val nameEn: Any,

    @SerializedName("nameOriginal")
    val nameOriginal: String,

    @SerializedName("posterUrl")
    val posterUrl: String,

    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String,

    @SerializedName("coverUrl")
    val coverUrl: String,

    @SerializedName("logoUrl")
    val logoUrl: Any,

    @SerializedName("reviewsCount")
    val reviewsCount: Int,

    @SerializedName("ratingGoodReview")
    val ratingGoodReview: Any,

    @SerializedName("ratingGoodReviewVoteCount")
    val ratingGoodReviewVoteCount: Int,

    @SerializedName("ratingKinopoisk")
    val ratingKinopoisk: Double,

    @SerializedName("ratingKinopoiskVoteCount")
    val ratingKinopoiskVoteCount: Int,

    @SerializedName("ratingImdb")
    val ratingImdb: Double,

    @SerializedName("ratingImdbVoteCount")
    val ratingImdbVoteCount: Int,

    @SerializedName("ratingFilmCritics")
    val ratingFilmCritics: Double,

    @SerializedName("ratingFilmCriticsVoteCount")
    val ratingFilmCriticsVoteCount: Int,

    @SerializedName("ratingAwait")
    val ratingAwait: Any,

    @SerializedName("ratingAwaitCount")
    val ratingAwaitCount: Int,

    @SerializedName("ratingRfCritics")
    val ratingRfCritics: Double,

    @SerializedName("ratingRfCriticsVoteCount")
    val ratingRfCriticsVoteCount: Int,

    @SerializedName("webUrl")
    val webUrl: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("filmLength")
    val filmLength: Int,

    @SerializedName("slogan")
    val slogan: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("shortDescription")
    val shortDescription: String,

    @SerializedName("editorAnnotation")
    val editorAnnotation: Any,

    @SerializedName("isTicketsAvailable")
    val isTicketsAvailable: Boolean,

    @SerializedName("productionStatus")
    val productionStatus: Any,

    @SerializedName("type")
    val type: String,

    @SerializedName("ratingMpaa")
    val ratingMpaa: String,

    @SerializedName("ratingAgeLimits")
    val ratingAgeLimits: String,

    @SerializedName("countries")
    val countries: List<Country>,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("startYear")
    val startYear: Any,

    @SerializedName("endYear")
    val endYear: Any,

    @SerializedName("serial")
    val serial: Boolean,

    @SerializedName("shortFilm")
    val shortFilm: Boolean,

    @SerializedName("completed")
    val completed: Boolean,

    @SerializedName("hasImax")
    val hasImax: Boolean,

    @SerializedName("has3D")
    val has3D: Boolean,

    @SerializedName("lastSync")
    val lastSync: String,
)