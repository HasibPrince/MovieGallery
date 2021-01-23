package com.mobile.moviegallery.data.api.models

import com.google.gson.annotations.SerializedName

data class MovieData(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?
)

data class MovieResponse(val page: Int, val results: List<MovieData>)

data class MovieDetailData(
    val id: Int,
    val title: String,
    val tagline: String,
    val status: String,
    @SerializedName("release_date")
    val releaseData: String,
    val revenue: Long,
    val overview: String,
    @SerializedName("backdrop_path")
    val backDropPath: String
)