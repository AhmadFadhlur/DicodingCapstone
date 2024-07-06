package com.ashoka.capstonedicoding.ui.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ashoka.core.data.remote.response.discover.ResultsTvShowItem
import com.ashoka.core.data.remote.response.search.ResultsSearchTvShowsItem
import com.ashoka.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
)  : ViewModel(){
    private val _dicoverTvShows = MutableStateFlow<PagingData<ResultsTvShowItem>>(PagingData.empty())
    val dicoverTvShows : MutableStateFlow<PagingData<ResultsTvShowItem>> = _dicoverTvShows

    private val _searchTvshows = MutableStateFlow<PagingData<ResultsSearchTvShowsItem>>(PagingData.empty())
    val searchTvshows : MutableStateFlow<PagingData<ResultsSearchTvShowsItem>> = _searchTvshows

    fun getDiscoverTvShows(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String) = viewModelScope.launch {
        movieUseCase.getTvShowDiscover(token, adultStatus, videoStatus, language, sortBy).cachedIn(viewModelScope)
            .collectLatest {
                _dicoverTvShows.value = it
            }
    }

    fun getSearchTvshows(
        token: String,
        q : String,
        adultStatus: Boolean,
        language: String,
    ) = viewModelScope.launch {
        movieUseCase.getSearchTvshows(token, q, adultStatus, language)
            .cachedIn(viewModelScope)
            .collectLatest {
                _searchTvshows.value = it

            }
    }
}