package com.example.eremeev.data.datasource.database

import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            FilmsDatabase::class.java,
            "Films.db"
        ).build()
    }

    single { get<FilmsDatabase>().getDao() }
}