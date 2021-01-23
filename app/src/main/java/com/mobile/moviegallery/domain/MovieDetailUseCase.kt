package com.mobile.moviegallery.domain

import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.MovieDetail
import com.mobile.moviegallery.data.repositories.IMovieRepository
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieRepository: IMovieRepository) :
    IMovieDetailUseCase {
     override suspend fun getMovieDetail(id: Int): Result<MovieDetail> {
        return movieRepository.getMovieDetail(id)
     }
}