package com.mobile.moviegallery.di

import com.mobile.moviegallery.domain.IMovieDetailUseCase
import com.mobile.moviegallery.domain.MovieDetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface UseCaseModule {
    @Binds
    fun getMovieDetailUseCase(movieDetialUseCaseModule: MovieDetailUseCase): IMovieDetailUseCase
}