package com.mobile.moviegallery.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mobile.moviegallery.data.api.Result
import com.mobile.moviegallery.databinding.FragmentMovieDetailPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailPageBinding
    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()
    var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt("movieId") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        movieDetailViewModel.getMovieDetail(movieId).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.progress.show()
                }
                Result.Status.SUCCESS -> {
                    binding.revenue.visibility = View.VISIBLE
                    binding.progress.visibility = View.INVISIBLE
                    binding.movieDetail = it.data
                    binding.progress.hide()
                }
                Result.Status.ERROR -> {
                    binding.progress.visibility = View.INVISIBLE
                    showErrorDialog(it.message)
                    binding.progress.hide()
                }
            }
        })
    }

    private fun showErrorDialog(message: String?) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .show()
    }


}