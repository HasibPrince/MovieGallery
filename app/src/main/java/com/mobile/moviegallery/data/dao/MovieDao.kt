package com.mobile.moviegallery.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.moviegallery.data.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieIfNotExistsOrUpdateIfChanged(movies: List<Movie>): List<Long>

    @Query("SELECT * FROM Movie WHERE page = :page")
    fun getMoviesByPage(page: Int): List<Movie>
}