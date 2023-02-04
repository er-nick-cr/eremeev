package com.example.eremeev.domain.entity

data class Film(
    val id: Int,
    val posterUrl: String,
    val name: String,
    val genre: String,
    val isFavorite: Boolean,
)