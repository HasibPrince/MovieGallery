package com.mobile.moviegallery.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moviegallery.data.model.Movie
import com.mobile.moviegallery.data.repositories.DIFF_ITEM_CALLBACK
import com.mobile.moviegallery.databinding.ItemMovieBinding
import timber.log.Timber

class MovieAdapter(private val selectListener: (Movie,FragmentNavigator.Extras) -> Unit) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, selectListener)
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, selectListener: (Movie,FragmentNavigator.Extras) -> Unit) {
            binding.movie = movie
            binding.shimmerView = binding.shimmer
            binding.root.setOnClickListener {
                Timber.d("===> movieId: ${movie.id}")
                val extras = FragmentNavigatorExtras(
                    binding.moviePoster to "image${movie.id}"
//                    binding.movieTitle to "title${movie.id}"
                )
                selectListener(movie, extras)
            }
        }
    }
}