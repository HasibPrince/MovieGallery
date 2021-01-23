package com.mobile.moviegallery.domain

import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.MovieDetail

interface IMovieDetailUseCase {
    suspend fun getMovieDetail(id: Int): Result<MovieDetail>
}