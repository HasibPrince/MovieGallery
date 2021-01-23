package com.mobile.moviegallery.di

import androidx.paging.PagingSource
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.repositories.MoviePagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface UtilityModule {
    @Singleton
    @Binds
    fun getPagingSource(moviePagingSource: MoviePagingSource): PagingSource<Int,Movie>
}