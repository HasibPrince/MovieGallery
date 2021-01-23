package com.mobile.moviegallery.ui.moviedetail

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.getMovieDetailList
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var movieDetailUseCase: FakeMovieDetailUseCase
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        movieDetailUseCase = FakeMovieDetailUseCase(getMovieDetailList())
        movieDetailViewModel = MovieDetailViewModel(movieDetailUseCase)
    }

    @Test
    fun getMovieDetail_success() = mainCoroutineRule.runBlockingTest{
        val movieDetailLiveData = movieDetailViewModel.getMovieDetail(12)
        var movieDetailData = movieDetailLiveData.getOrAwaitValue()


        assertThat(movieDetailData.status, `is`(Result.Status.LOADING))
        movieDetailData = movieDetailLiveData.getOrAwaitValue()

        assertThat(movieDetailData.status, `is`(Result.Status.SUCCESS))
        assertThat(movieDetailData.data?.id, `is`(12))
    }

    @Test
    fun getMovieDetail_error() = mainCoroutineRule.runBlockingTest{
        movieDetailUseCase.setReturnError(true)
        val movieDetailLiveData = movieDetailViewModel.getMovieDetail(12)
        var movieDetailData = movieDetailLiveData.getOrAwaitValue()

        assertThat(movieDetailData.status, `is`(Result.Status.LOADING))
        movieDetailData = movieDetailLiveData.getOrAwaitValue()

        assertThat(movieDetailData.status, `is`(Result.Status.ERROR))
    }

    @Test
    fun getMovieDetail_emptyMovieList() = mainCoroutineRule.runBlockingTest{
        movieDetailUseCase.movieDetailList.clear()

        val movieDetailLiveData = movieDetailViewModel.getMovieDetail(12)
        var movieDetailData = movieDetailLiveData.getOrAwaitValue()

        assertThat(movieDetailData.status, `is`(Result.Status.LOADING))
        movieDetailData = movieDetailLiveData.getOrAwaitValue()

        assertThat(movieDetailData.status, `is`(Result.Status.ERROR))
    }
}