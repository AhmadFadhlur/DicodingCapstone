package com.ashoka.capstonedicoding.ui.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ashoka.core.data.remote.repository.search.SearchRepository
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMoviesViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _dicoverMovies = MutableStateFlow<PagingData<ResultMovieItem>>(PagingData.empty())
    val discoverMovies : MutableStateFlow<PagingData<ResultMovieItem>> = _dicoverMovies

    private val _searchMovies = MutableStateFlow<PagingData<ResultMovieItem>>(PagingData.empty())
    val searchMovies : MutableStateFlow<PagingData<ResultMovieItem>> = _searchMovies

    fun getDiscoverMovies(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String) = viewModelScope.launch {
            movieUseCase.getMovieDiscover(token, adultStatus, videoStatus, language, sortBy).cachedIn(viewModelScope)
                .collectLatest {
                    _dicoverMovies.value = it
                }

    }

//    fun getSearchMovies(
//        token: String,
//        q: String,
//        adultStatus: Boolean,
//        language: String
//    ) = viewModelScope.launch {
//        searchRepository.searchMovie(token, q, adultStatus, language)
//            .cachedIn(viewModelScope)
//            .collectLatest {
//                _searchMovies.value = it
//                Log.d("getSearchMovies()", "_searchMovies = $it}")
//            }
//    }
}