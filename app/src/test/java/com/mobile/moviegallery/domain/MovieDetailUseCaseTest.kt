package com.mobile.moviegallery.domain

import MainCoroutineRule
import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.model.MovieDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class MovieDetailUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var movieList: List<Movie>
    private lateinit var movieDetailList: MutableList<MovieDetail>

    private lateinit var movieRepository: FakeMovieRepository
    @Before
    fun setUp() {
        movieList = listOf(
            Movie(11, "movie11", "", 1),
            Movie(12, "movie12", "", 2),
            Movie(13, "movie13", "", 3),
            Movie(14, "movie14", "", 4),
            Movie(15, "movie15", "", 5),
            Movie(16, "movie16", "", 6)
        )

        movieDetailList = mutableListOf(
            MovieDetail(11, "movie11", "","", "", 100, "", ""),
            MovieDetail(12, "movie12", "","", "", 100, "", ""),
            MovieDetail(13, "movie13", "","", "", 100, "", ""),
            MovieDetail(14, "movie14", "","", "", 100, "", ""),
            MovieDetail(15, "movie15", "","", "", 100, "", ""),
            MovieDetail(16, "movie16", "","", "", 100, "", "")
        )

        movieRepository = FakeMovieRepository(movieList, movieDetailList)
    }

    @Test
    fun getMovieDetail_success() = mainCoroutineRule.runBlockingTest {
        val movieDetail = movieRepository.getMovieDetail(12)
        assertThat(movieDetail.status, `is`(Result.Status.SUCCESS))
        assertThat(movieDetail.data?.id, `is`(12))
    }

    @Test
    fun getMovieDetail_error() = mainCoroutineRule.runBlockingTest {
        movieRepository.setReturnError(true)
        val movieDetail = movieRepository.getMovieDetail(12)
        assertThat(movieDetail.status, `is`(Result.Status.ERROR))
    }

    @Test
    fun getMovieDetail_empty_error() = mainCoroutineRule.runBlockingTest {
        movieDetailList.clear()
        val movieDetail = movieRepository.getMovieDetail(12)
        assertThat(movieDetail.status, `is`(Result.Status.ERROR))
    }
}