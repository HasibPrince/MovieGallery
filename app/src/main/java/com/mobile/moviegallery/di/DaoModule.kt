package com.mobile.moviegallery.di

import android.content.Context
import com.mobile.moviegallery.data.RetrofitInstanceKT
import com.mobile.moviegallery.data.api.BASE_URL
import com.mobile.moviegallery.data.api.MovieService
import com.mobile.moviegallery.data.dao.MovieDao
import com.mobile.moviegallery.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DaoModule {
    @Provides
    @Singleton
    fun getMovieDao(@ApplicationContext context: Context): MovieDao {
        return AppDatabase.getInstance(context).getMovieDao()
    }
}