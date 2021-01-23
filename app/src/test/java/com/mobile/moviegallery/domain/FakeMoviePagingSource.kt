package com.mobile.moviegallery.domain

import androidx.paging.PagingSource
import com.mobile.moviegallery.data.model.Movie
import java.lang.Exception

class FakeMoviePagingSource(var movieList: List<Movie>) : PagingSource<Int, Movie>() {
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        if(!shouldReturnError && movieList.isNotEmpty()) {
            return LoadResult.Page(movieList,
                if(page == 1) null else page - 1,
                page + 1
            )
        }
        return LoadResult.Error(Exception("Error Occurred"))
    }
}