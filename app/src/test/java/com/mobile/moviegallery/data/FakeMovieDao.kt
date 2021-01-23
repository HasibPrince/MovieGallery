package com.mobile.moviegallery.data

import com.mobile.moviegallery.data.dao.MovieDao
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail

class FakeMovieDao(val movieList: MutableList<Movie>,
                   val movieDetailList: MutableList<MovieDetail>) : MovieDao {
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun addMovieIfNotExistsOrUpdateIfChanged(movies: List<Movie>): List<Long> {
        movies.map {
            val foundMovie = movieList.find { movie -> movie.id == it.id }
            if(foundMovie == null) {
                movieList.add(it)
            }
        }
        return listOf()
    }

    override suspend fun getMoviesByPage(page: Int): List<Movie> {
        return movieList.filter { it.page == page }
    }

    override suspend fun addMovieDetail(movieDetail: MovieDetail): Long {
        movieDetailList.add(movieDetail)
        return 234L
    }

    override suspend fun getMovieDetailPage(id: Int): MovieDetail? {
        return movieDetailList.find { it.id == id }
    }
}