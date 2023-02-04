package com.example.eremeev.data.datasource.database

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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