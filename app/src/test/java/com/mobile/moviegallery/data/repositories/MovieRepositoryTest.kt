package com.mobile.moviegallery.data.repositories

import MainCoroutineRule
import com.mobile.moviegallery.data.*
import com.mobile.moviegallery.data.api.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var fakeMovieService: FakeMovieService
    lateinit var fakeMovieDao: FakeMovieDao
    lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        fakeMovieService = FakeMovieService(getMovieList(), getMovieDetailList())
        fakeMovieDao = FakeMovieDao(getMovieList(), getMovieDetailList())
        movieRepository = MovieRepository(fakeMovieService, fakeMovieDao)
    }

    @Test
    fun getMoviesByPage_success()=  mainCoroutineRule.runBlockingTest{
        val movieData = movieRepository.getMoviesByPage(2)
        assertThat(movieData.status, `is`(Result.Status.SUCCESS))
        assertThat(movieData.data?.size, `is`(2))
    }

    @Test
    fun getMoviesByPage_movieDaoEmpty_success()=  mainCoroutineRule.runBlockingTest{
        fakeMovieDao.movieList.clear()
        assertThat(fakeMovieDao.movieList.size, `is`(0))
        val movieData = movieRepository.getMoviesByPage(2)
        assertThat(movieData.status, `is`(Result.Status.SUCCESS))
        assertThat(fakeMovieDao.movieList.size, `is`(2))
    }

    @Test
    fun getMoviesByPage_apiError()=  mainCoroutineRule.runBlockingTest{
        fakeMovieDao.movieList.clear()
        fakeMovieService.setReturnError(true)
        val movieData = movieRepository.getMoviesByPage(2)
        assertThat(movieData.status, `is`(Result.Status.ERROR))
    }

    @Test
    fun getMovieDetailByPage_success()=  mainCoroutineRule.runBlockingTest{
        val movieData = movieRepository.getMovieDetail(15)
        assertThat(movieData.status, `is`(Result.Status.SUCCESS))
    }

    @Test
    fun getMovieDetailById_movieDaoEmpty_success()=  mainCoroutineRule.runBlockingTest{
        fakeMovieDao.movieDetailList.clear()
        assertThat(fakeMovieDao.movieDetailList.size, `is`(0))
        val movieData = movieRepository.getMovieDetail(15)
        assertThat(movieData.status, `is`(Result.Status.SUCCESS))
        assertThat(fakeMovieDao.movieDetailList.size, `is`(1))
    }

    @Test
    fun getMovieDetailById_apiError()=  mainCoroutineRule.runBlockingTest{
        fakeMovieDao.movieDetailList.clear()
        fakeMovieService.setReturnError(true)
        val movieData = movieRepository.getMovieDetail(15)
        assertThat(movieData.status, `is`(Result.Status.ERROR))
    }
}