package com.example.eremeev.presentation.main.entity

sealed interface FilmsTabs {
    object Top : FilmsTabs
    object Favorites : FilmsTabs
}