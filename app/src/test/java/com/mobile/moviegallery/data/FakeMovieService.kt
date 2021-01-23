package com.mobile.moviegallery.data

import com.mobile.moviegallery.data.api.MovieService
import com.mobile.moviegallery.data.api.models.MovieData
import com.mobile.moviegallery.data.api.models.MovieDetailData
import com.mobile.moviegallery.data.api.models.MovieResponse
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail
import retrofit2.Response

class FakeMovieService(
    private val movieList: List<Movie>,
    private val movieDetailList: List<MovieDetail>
) : MovieService {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getMovies(apiKey: String, page: Int): Response<MovieResponse> {
        if(shouldReturnError) {
            return Response.error(400, null)
        }
        val movies = movieList.filter { it.page == page }
        val movieData = movies.map { MovieData(it.id, it.name, it.posterPath) }
        val movieResponse = MovieResponse(page, movieData)
        return Response.success(movieResponse)
    }

    override suspend fun getMovieDetail(id: Int, apiKey: String): Response<MovieDetailData> {
        if(shouldReturnError) {
            return Response.error(400, null)
        }
        val movieDetail = movieDetailList.find { it.id == id } ?: return Response.error(404, null)
        val movieDetailData = MovieDetailData(
            movieDetail.id, movieDetail.title, movieDetail.tagline, movieDetail.status,
            movieDetail.releaseData, movieDetail.revenue, movieDetail.overview, movieDetail.backDropPath
        )

        return Response.success(movieDetailData)
    }
}