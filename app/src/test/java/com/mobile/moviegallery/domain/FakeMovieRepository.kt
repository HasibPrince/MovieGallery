package com.mobile.moviegallery.domain

import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail
import com.mobile.moviegallery.data.repositories.IMovieRepository

class FakeMovieRepository(
    private val movieList: List<Movie>,
    private val movieDetailList: List<MovieDetail>
) : IMovieRepository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getMoviesByPage(page: Int): Result<List<Movie>> {
        if (shouldReturnError) {
            return Result.error("Test Error")
        }
        if (movieList.isNotEmpty()) {
            return Result.success(
                movieList.filter { movie -> movie.page == page }
            )
        }
        return Result.error("Data not found")
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetail> {
        if (shouldReturnError) {
            return Result.error("Test Error")
        }
        val movieDetail = movieDetailList.find { it.id == id }
        if (movieDetail != null) {
            return Result.success(movieDetail)
        }
        return Result.error("Data not found")
    }
}