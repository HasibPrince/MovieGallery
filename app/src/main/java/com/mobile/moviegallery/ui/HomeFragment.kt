package com.mobile.moviegallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.mobile.moviegallery.databinding.FragmentHomeBinding
import com.mobile.moviegallery.ui.adapters.ItemDecoration
import com.mobile.moviegallery.ui.adapters.MovieAdapter
import com.mobile.ninetypercent.ui.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val movieAdapter = MovieAdapter()
    private lateinit var binding: FragmentHomeBinding
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRV.adapter = movieAdapter
        binding.movieListRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.movieListRV.addItemDecoration(
            ItemDecoration(
                ViewUtils.dpToPx(requireContext(), 20),
                ViewUtils.dpToPx(requireContext(), 20)
            )
        )
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                movieViewModel.fetchPosts().collectLatest {
                    movieAdapter.submitData(it)
                }
            }
        }
    }
}