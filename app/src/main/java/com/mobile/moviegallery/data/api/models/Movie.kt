package com.mobile.moviegallery.data.api.models

import com.google.gson.annotations.SerializedName

data class MovieData(val id: Int,
                     val title: String,
                     @SerializedName("poster_path")
                     val posterPath: String?)

data class MovieResponse(val page: Int, val results: List<MovieData>)