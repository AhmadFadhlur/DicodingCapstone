package com.ashoka.capstonedicoding.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.adapter.LoadingStateMovieAdapter
import com.ashoka.capstonedicoding.databinding.FragmentHomeMoviesBinding
import com.ashoka.core.adapter.MovieAdapter
import com.ashoka.core.utils.EndPointMovie.TOKEN
import com.ferfalk.simplesearchview.SimpleSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeMoviesFragment : Fragment(R.layout.fragment_home_movies) {

    private val binding by viewBinding(FragmentHomeMoviesBinding::bind)
    private val homeMoviesViewModel : HomeMoviesViewModel by viewModels()
    private  lateinit var  movieAdapter : MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieAdapter()
        observeDiscoverMovie()
//        setSearcView()


    }


    private fun setMovieAdapter() = with(binding){
        movieAdapter = MovieAdapter()
        rvMoviesHome.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.d("setMovieAdapter()", "movieAdapter = $movieAdapter}")
        rvMoviesHome.adapter = movieAdapter.withLoadStateFooter(
            footer = LoadingStateMovieAdapter {movieAdapter.retry()}
        )

    }

//    private fun setSearcView() = with(binding){
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (!query.isNullOrEmpty()) { onStartQuery(query)
//                    Log.d("setSearcView() if = true", "query = $query}")
//                } else observeDiscoverMovie()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                Log.d("setSearcView() txt chng", "query = $newText}")
//                if (newText.isNullOrEmpty()) observeDiscoverMovie()
//                return true
//            }
//
//        })
//    }
//    private fun onStartQuery(query: String) {
//        Log.d("onStartQuery()", "query = $query")
//        homeMoviesViewModel.getSearchMovies(
//            token = "Bearerawr $TOKEN",
//            q = query,
//            adultStatus = false,
//            language = "en-US"
//        )
//        observeSearchQuery()
//    }

    private fun observeSearchQuery() = with(binding){
        lifecycleScope.launchWhenStarted {
            homeMoviesViewModel.searchMovies.collectLatest {
                Log.d("collectSearch{", "paging data = $it}")

                movieAdapter.submitData(it)
            }
        }
    }

    private fun observeDiscoverMovie() = with(binding){
        Log.d("observeDiscoverMovie()", "masuk sini")
        homeMoviesViewModel.getDiscoverMovies(
            token = "Bearer $TOKEN",
            adultStatus = false,
            videoStatus = false,
            language = "en-US",
            sortBy = "popularity.desc")

//        homeMoviesViewModel.discoverMovies.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
//            .onEach(movieAdapter::submitData).launchIn(viewLifecycleOwner.lifecycleScope)

        lifecycleScope.launchWhenStarted {
            homeMoviesViewModel.discoverMovies.collectLatest {
                Log.d("collectLatest{", "paging data = $it}")
                movieAdapter.submitData(it)

            }
        }
    }

}