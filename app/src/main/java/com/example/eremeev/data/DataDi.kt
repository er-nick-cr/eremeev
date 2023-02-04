package com.example.eremeev.data

import com.example.eremeev.data.datasource.database.DatabaseDatasource
import com.example.eremeev.data.datasource.network.NetworkDatasource
import com.example.eremeev.data.repository.FilmsRepositoryImpl
import com.example.eremeev.domain.repository.FilmsRepository
import org.koin.dsl.module

val dataModule = module {
    single { NetworkDatasource(get()) }
    single { DatabaseDatasource(get()) }
    single<FilmsRepository> { FilmsRepositoryImpl(get(), get()) }
}