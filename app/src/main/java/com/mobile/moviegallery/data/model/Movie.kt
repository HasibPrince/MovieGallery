package com.mobile.moviegallery.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Movie", indices = [Index("id", unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val posterPath: String,
    var page: Int
) {
    fun getPosterUrl(): String { //TODO refactor posterpath
        return "https://image.tmdb.org/t/p/w342$posterPath"
    }
}
