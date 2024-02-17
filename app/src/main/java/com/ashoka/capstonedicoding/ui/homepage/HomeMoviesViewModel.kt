package com.ashoka.capstonedicoding.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ashoka.core.data.remote.repository.discover.DiscoverRepository
import com.ashoka.core.data.remote.response.ResultMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMoviesViewModel @Inject constructor(
    private val discoverRepository: DiscoverRepository
) : ViewModel() {

    private val _dicoverMovies = MutableStateFlow<PagingData<ResultMovieItem>>(PagingData.empty())
    val discoverMovies : MutableStateFlow<PagingData<ResultMovieItem>> = _dicoverMovies

    fun getDiscoverMovies(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String) = viewModelScope.launch {
            discoverRepository.discoverMovie(token, adultStatus, videoStatus, language)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _dicoverMovies.value = it
                }
    }
}