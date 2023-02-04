package com.example.eremeev.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eremeev.data.datasource.database.entity.FilmDbModel

@Database(entities = [FilmDbModel::class], version = 1)
abstract class FilmsDatabase : RoomDatabase() {

    abstract fun getDao(): FilmsDao
}