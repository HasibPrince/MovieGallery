package com.mobile.moviegallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moviegallery.R
import com.mobile.moviegallery.common.extensions.safeNavigate
import com.mobile.moviegallery.databinding.FragmentHomeBinding
import com.mobile.moviegallery.ui.adapters.ItemDecoration
import com.mobile.moviegallery.ui.adapters.MovieAdapter
import com.mobile.ninetypercent.ui.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var movieAdapter: MovieAdapter

    private lateinit var binding: FragmentHomeBinding
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieAdapter { movie, extra ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailPage(movie.id)
            findNavController().safeNavigate(R.id.homeFragment, action)
        }
        movieAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
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
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        setupMovieRecyclerView()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                movieViewModel.fetchPosts().collectLatest {
                    movieAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupMovieRecyclerView() {
        binding.movieListRV.adapter = movieAdapter
        val spanCount = resources.getInteger(R.integer.span_count)
        binding.movieListRV.layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.movieListRV.addItemDecoration(
            ItemDecoration(
                ViewUtils.dpToPx(requireContext(), 20),
                ViewUtils.dpToPx(requireContext(), 15)
            )
        )
    }
}