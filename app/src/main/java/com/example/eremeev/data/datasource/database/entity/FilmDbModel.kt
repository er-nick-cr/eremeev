package com.example.eremeev.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "posterUrl")
    val posterUrl: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,
)