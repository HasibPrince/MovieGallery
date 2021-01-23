package com.mobile.moviegallery.data

import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail

fun getMovieList(): MutableList<Movie> {
    return mutableListOf(
        Movie(11, "movie11", "", 1),
        Movie(12, "movie12", "", 1),
        Movie(13, "movie13", "", 2),
        Movie(14, "movie14", "", 2),
        Movie(15, "movie15", "", 3),
        Movie(16, "movie16", "", 3)
    )
}

fun getMovieDetailList(): MutableList<MovieDetail> {
    return mutableListOf(
        MovieDetail(11, "movie11", "", "", "", 100, "", ""),
        MovieDetail(12, "movie12", "", "", "", 100, "", ""),
        MovieDetail(13, "movie13", "", "", "", 100, "", ""),
        MovieDetail(14, "movie14", "", "", "", 100, "", ""),
        MovieDetail(15, "movie15", "", "", "", 100, "", ""),
        MovieDetail(16, "movie16", "", "", "", 100, "", "")
    )
}