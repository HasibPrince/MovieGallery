package com.mobile.moviegallery.data.repositories

import androidx.paging.PagingSource
import com.mobile.moviegallery.common.extensions.toJsonString
import com.mobile.moviegallery.data.model.Movie
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(private val movieRepository: MovieRepository) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        Timber.d("===> prams ${params.toJsonString()}")
        val movieResult = movieRepository.getMoviesByPage(page)

        if(movieResult.isSuccess() && movieResult.data != null) {
            return LoadResult.Page(movieResult.data,
                if(page == 1) null else page - 1,
                page + 1
            )
        }

        return LoadResult.Error(Exception("Error Occurred"))
    }

    override val keyReuseSupported: Boolean
        get() = true
}