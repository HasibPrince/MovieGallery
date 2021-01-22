package com.mobile.moviegallery.data.api

import com.mobile.moviegallery.data.api.models.MovieResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}