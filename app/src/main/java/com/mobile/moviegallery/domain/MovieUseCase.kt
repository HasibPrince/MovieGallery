package com.mobile.moviegallery.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.repositories.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val moviePagingSource: PagingSource<Int, Movie>) {
    fun fetchMovies(): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(20, enablePlaceholders = false)
        ) {
            moviePagingSource
        }.flow
    }
}