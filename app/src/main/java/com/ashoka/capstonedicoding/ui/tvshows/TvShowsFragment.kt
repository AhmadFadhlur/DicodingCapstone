package com.ashoka.capstonedicoding.ui.tvshows

import android.os.Bundle
import android.util.Log
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
import com.ashoka.capstonedicoding.databinding.FragmentTvShowsBinding
import com.ashoka.capstonedicoding.utils.False
import com.ashoka.capstonedicoding.utils.TOKEN
import com.ashoka.capstonedicoding.utils.True
import com.ashoka.capstonedicoding.utils.setVisibleOrGone
import com.ashoka.core.adapter.SearchTvshowsAdapter
import com.ashoka.core.adapter.TvShowsAdapter
import com.ashoka.core.utils.EndPointMovie.ID_MOVIE
import com.ashoka.core.utils.EndPointMovie.TO_DETAIL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TvShowsFragment : Fragment(R.layout.fragment_tv_shows) {

    private val binding by viewBinding(FragmentTvShowsBinding::bind)
    private lateinit var tvShowsAdapter: TvShowsAdapter
    private val tvShowsViewModel : TvShowsViewModel by viewModels()
    private lateinit var searchTvshowsAdapter: SearchTvshowsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
        observeDiscoverTvshows()
    }



    private fun setSearchAdapter() = with(binding){
        searchTvshowsAdapter = SearchTvshowsAdapter{
            val idMovie = it.id
            if (idMovie != null) {
                val idBundle = Bundle().apply {
                    putInt(ID_MOVIE, idMovie)
                    putString(TO_DETAIL, "tvshows")

                }
                findNavController().navigate(R.id.action_tvShowsFragment_to_detailMoviesFragment, idBundle)
            }
        }
        rvTvShows.setVisibleOrGone(Boolean.False)
        rvTvshowsSearchHome.setVisibleOrGone(Boolean.True)
        rvTvshowsSearchHome.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvTvshowsSearchHome.adapter = searchTvshowsAdapter.withLoadStateFooter(
            footer = LoadingStateMovieAdapter {
                searchTvshowsAdapter.retry()}
        )
    }

    private fun setTvshowsAdapter() = with(binding){
        rvTvshowsSearchHome.setVisibleOrGone(Boolean.False)
        rvTvShows.setVisibleOrGone(Boolean.True)
        tvShowsAdapter = TvShowsAdapter {
            val idMovie = it.id
            if (idMovie != null) {
                val idBundle = Bundle().apply {
                    putInt(ID_MOVIE, idMovie)
                    putString(TO_DETAIL, "tvshows")
                }
                findNavController().navigate(R.id.action_tvShowsFragment_to_detailMoviesFragment, idBundle)
            }
        }
        rvTvShows.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.d("setMovieAdapter()", "movieAdapter = $tvShowsAdapter}")
        rvTvShows.adapter = tvShowsAdapter.withLoadStateFooter(
            footer = LoadingStateMovieAdapter {tvShowsAdapter.retry()}
        )
    }
    private fun setSearchView() = with(binding){
        searchView.setOnQueryTextListener(object  : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) onStartQuery(query) else observeDiscoverTvshows()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty())  observeDiscoverTvshows()
                return true
            }

        })

    }
    private fun onStartQuery(query: String) {
        tvShowsViewModel.getSearchTvshows(
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
            tvShowsViewModel.searchTvshows.collectLatest {
                searchTvshowsAdapter.submitData(it)
            }
        }
    }

    private fun observeDiscoverTvshows() = with(binding){
        setTvshowsAdapter()
        tvShowsViewModel.getDiscoverTvShows(
            token = "Bearer ${BuildConfig.API_KEY}",
            adultStatus = false,
            videoStatus = false,
            language = "en-US",
            sortBy = "popularity.desc")
        lifecycleScope.launchWhenStarted {
            tvShowsViewModel.dicoverTvShows.collectLatest {
                Log.d("collectLatest{", "paging data = $it}")
                tvShowsAdapter.submitData(it)
            }
        }
    }
}
