package com.example.eremeev.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.eremeev.data.datasource.database.entity.FilmDbModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FilmsDao {

    @Insert(onConflict = REPLACE)
    fun addToFavorite(film: FilmDbModel): Completable

    @Query("DELETE FROM films WHERE id = :id")
    fun deleteFromFavorite(id: Int): Completable

    @Query("SELECT * FROM films")
    fun getFilms(): Single<List<FilmDbModel>>

    @Query("SELECT EXISTS (SELECT 1 FROM films WHERE id= :id)")
    fun checkIsFavorite(id: Int): Single<Boolean>
}