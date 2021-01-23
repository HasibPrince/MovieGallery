package com.mobile.moviegallery.di

import com.mobile.moviegallery.data.repositories.IMovieRepository
import com.mobile.moviegallery.data.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun getMovieRepository(movieRepository: MovieRepository): IMovieRepository
}