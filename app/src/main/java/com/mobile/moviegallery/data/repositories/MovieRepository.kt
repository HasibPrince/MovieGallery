package com.mobile.moviegallery.data.repositories

import com.mobile.moviegallery.data.api.API_TOKEN
import com.mobile.moviegallery.data.api.MovieService
import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.api.getResult
import com.mobile.moviegallery.data.dao.MovieDao
import com.mobile.moviegallery.data.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {
    private suspend fun fetchMovies(page: Int): Result<List<Movie>> {
        var movies = listOf<Movie>()
        val movieResult = getResult {
            movieService.getMovies(API_TOKEN, page)
        }

        if (!movieResult.isSuccess()) {
            return Result.error(movieResult.message ?: "Unknown Error")
        }

        movieResult.data?.let {
            movies = it.results.map { movie ->
                movie.toMovie(it.page)
            }
            movieDao.addMovieIfNotExistsOrUpdateIfChanged(movies)
            return Result.success(movies)
        }

        return Result.error("Unknown Error")
    }

    suspend fun getMoviesByPage(page: Int): Result<List<Movie>> {
        val movies = movieDao.getMoviesByPage(page)
        return if (movies.isNullOrEmpty()) {
            return fetchMovies(page)
        } else {
            Result.success(movies)
        }
    }
}