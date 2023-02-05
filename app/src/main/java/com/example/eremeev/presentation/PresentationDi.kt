package com.example.eremeev.presentation

import com.example.eremeev.presentation.detailed.DetailedFilmViewModel
import com.example.eremeev.presentation.main.FilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { FilmsViewModel(get()) }
    viewModel { DetailedFilmViewModel(get()) }
}