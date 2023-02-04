package com.example.eremeev.presentation

import android.app.Application
import com.example.eremeev.data.dataModule
import com.example.eremeev.data.datasource.database.databaseModule
import com.example.eremeev.data.datasource.network.networkModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FilmsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(dataModule, networkModule, presentationModule, databaseModule)

        startKoin {
            androidContext(this@FilmsApplication)
            modules(moduleList)
        }
    }
}