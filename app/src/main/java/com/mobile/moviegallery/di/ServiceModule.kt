package com.mobile.moviegallery.di

import com.mobile.moviegallery.data.api.RetrofitInstanceKT
import com.mobile.moviegallery.data.api.BASE_URL
import com.mobile.moviegallery.data.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ServiceModule {
    @Provides
    @Singleton
    fun getMovieService(): MovieService {
        return RetrofitInstanceKT.getRetrofitInstance(BASE_URL).create(MovieService::class.java)
    }
}