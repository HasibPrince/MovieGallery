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
    fun fetchPosts(): Flow<PagingData<Movie>> {
        return movieUseCase.fetchMovies()
    }
}