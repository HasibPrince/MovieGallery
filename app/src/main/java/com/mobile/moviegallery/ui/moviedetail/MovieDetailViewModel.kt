package com.mobile.moviegallery.ui.moviedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.data.model.MovieDetail
import com.mobile.moviegallery.domain.IMovieDetailUseCase
import com.mobile.moviegallery.domain.MovieDetailUseCase

class MovieDetailViewModel @ViewModelInject constructor(private val movieDetailUseCase: IMovieDetailUseCase) :
    ViewModel() {


    fun getMovieDetail(id: Int): LiveData<Result<MovieDetail>> {
        return liveData {
            emit(Result.loading())
            emit(movieDetailUseCase.getMovieDetail(id))
        }
    }
}