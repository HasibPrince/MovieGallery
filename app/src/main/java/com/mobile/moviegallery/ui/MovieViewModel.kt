package com.mobile.moviegallery.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.domain.MovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MovieViewModel @ViewModelInject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _movieLiveData = MutableLiveData<PagingData<Movie>>()
    val movieLiveData: LiveData<PagingData<Movie>>
    get() = _movieLiveData

    fun fetchPosts() {
        viewModelScope.launch {
            val movieResult = movieUseCase.fetchMovies()
            movieResult.collectLatest{
                _movieLiveData.value = it
            }
        }
    }
}