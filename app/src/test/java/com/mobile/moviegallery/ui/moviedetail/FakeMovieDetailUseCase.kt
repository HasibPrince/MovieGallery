package com.mobile.moviegallery.ui.moviedetail

import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.MovieDetail
import com.mobile.moviegallery.domain.IMovieDetailUseCase

class FakeMovieDetailUseCase(val movieDetailList: MutableList<MovieDetail>): IMovieDetailUseCase {
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetail> {
        if(shouldReturnError) {
            return Result.error("Test exception")
        }
        val movieDetail = movieDetailList.find { it.id == id } ?: return Result.error("Not found")
        return Result.success(movieDetail)
    }
}