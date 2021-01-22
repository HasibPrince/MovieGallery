package com.mobile.moviegallery.data.repositories

import androidx.recyclerview.widget.DiffUtil
import com.mobile.moviegallery.data.api.models.MovieData
import com.mobile.moviegallery.data.model.Movie

fun MovieData.toMovie(page: Int): Movie {
    return Movie(this.id, this.title, this.posterPath ?: "", page)
}

val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.id == newItem.id &&
                oldItem.posterPath == newItem.posterPath &&
                oldItem.page == newItem.page
    }

}
