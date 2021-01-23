package com.mobile.moviegallery.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MovieDetail", indices = [Index("id", unique = true)])
data class MovieDetail(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val tagline: String,
    val status: String,
    val releaseData: String,
    val revenue: Long,
    val overview: String,
    val backDropPath: String
) {
    fun getBannerUrl(): String { //TODO refactor posterpath
        return "https://image.tmdb.org/t/p/w342$backDropPath"
    }
}