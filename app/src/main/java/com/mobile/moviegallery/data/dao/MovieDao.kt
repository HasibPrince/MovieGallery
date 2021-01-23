package com.mobile.moviegallery.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieIfNotExistsOrUpdateIfChanged(movies: List<Movie>): List<Long>

    @Query("SELECT * FROM Movie WHERE page = :page")
    suspend fun getMoviesByPage(page: Int): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieDetail(movieDetail: MovieDetail): Long

    @Query("SELECT * FROM MovieDetail WHERE id = :id")
    suspend fun getMovieDetailPage(id: Int): MovieDetail?

}