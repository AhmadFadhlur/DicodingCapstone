package com.ashoka.capstonedicoding.ui.homepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.BuildConfig
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.adapter.LoadingStateMovieAdapter
import com.ashoka.capstonedicoding.databinding.FragmentHomeMoviesBinding
import com.ashoka.capstonedicoding.utils.False
import com.ashoka.capstonedicoding.utils.TOKEN
import com.ashoka.capstonedicoding.utils.True
import com.ashoka.capstonedicoding.utils.setVisibleOrGone
import com.ashoka.core.adapter.MovieAdapter
import com.ashoka.core.adapter.SearchMovieAdapter
import com.ashoka.core.utils.EndPointMovie.ID_MOVIE
import com.ashoka.core.utils.EndPointMovie.TO_DETAIL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeMoviesFragment : Fragment(R.layout.fragment_home_movies) {

    private val binding by viewBinding(FragmentHomeMoviesBinding::bind)
    private val homeMoviesViewModel : HomeMoviesViewModel by viewModels()
    private  lateinit var  movieAdapter : MovieAdapter
    private lateinit var searchMovieAdapter: SearchMovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
        observeDiscoverMovie()
    }

    private fun setSearchAdapter() = with(binding){
        searchMovieAdapter = SearchMovieAdapter{
            val idMovie = it.id
            if (idMovie != null) {
                val idBundle = Bundle().apply {
                    putInt(ID_MOVIE, idMovie)
                    putString(TO_DETAIL, "movie")

                }
                findNavController().navigate(R.id.action_main_navigation_to_detailMoviesFragment, idBundle)
            }
        }
        rvMoviesHome.setVisibleOrGone(Boolean.False)
        rvMoviesSearchHome.setVisibleOrGone(Boolean.True)
        rvMoviesSearchHome.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvMoviesSearchHome.adapter = searchMovieAdapter.withLoadStateFooter(
            footer = LoadingStateMovieAdapter {
                searchMovieAdapter.retry()}
        )
    }

    private fun setMovieAdapter() = with(binding){
        rvMoviesSearchHome.setVisibleOrGone(Boolean.False)
        rvMoviesHome.setVisibleOrGone(Boolean.True)
        movieAdapter = MovieAdapter{
            val idMovie = it.id
            if (idMovie != null) {
                val idBundle = Bundle().apply {
                    putInt(ID_MOVIE, idMovie.toInt())
                    putString(TO_DETAIL, "movie")
                }
                findNavController().navigate(R.id.action_main_navigation_to_detailMoviesFragment, idBundle)
            }
        }
        rvMoviesHome.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvMoviesHome.adapter = movieAdapter.withLoadStateFooter(
            footer = LoadingStateMovieAdapter {movieAdapter.retry()}
        )
    }

    private fun setSearchView() = with(binding){
        searchView.setOnQueryTextListener(object  : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) onStartQuery(query) else observeDiscoverMovie()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty())  observeDiscoverMovie()
                return true
            }

        })

    }
    private fun onStartQuery(query: String) {
        homeMoviesViewModel.getSearchMovies(
            token = "Bearer $TOKEN",
            q = query,
            adultStatus = false,
            language = "en-US"
        )
        setSearchAdapter()
        observeSearchQuery()
    }

    private fun observeSearchQuery() = with(binding){
        lifecycleScope.launchWhenStarted {
            homeMoviesViewModel.searchMovies.collectLatest {
                searchMovieAdapter.submitData(it)
            }
        }
    }


    private fun observeDiscoverMovie() = with(binding){
        setMovieAdapter()
        homeMoviesViewModel.getDiscoverMovies(
            token = "Bearer ${BuildConfig.API_KEY}",
            adultStatus = false,
            videoStatus = false,
            language = "en-US",
            sortBy = "popularity.desc")
        lifecycleScope.launchWhenStarted {
            homeMoviesViewModel.discoverMovies.collectLatest {movieAdapter.submitData(it)}
        }

    }
}