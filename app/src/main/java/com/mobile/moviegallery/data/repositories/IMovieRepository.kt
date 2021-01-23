package com.mobile.moviegallery.data.repositories

import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail

interface IMovieRepository {
    suspend fun getMoviesByPage(page: Int): Result<List<Movie>>

    suspend fun getMovieDetail(id: Int): Result<MovieDetail>
}