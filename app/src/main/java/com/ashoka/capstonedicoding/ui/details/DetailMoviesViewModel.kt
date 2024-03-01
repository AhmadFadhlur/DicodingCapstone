package com.ashoka.capstonedicoding.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.resource.ApiSourceResponse
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMoviesViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private var _detailMovie = MutableStateFlow<Resource<DetailMovie>?>(null)
    val detailMovie : MutableStateFlow<Resource<DetailMovie>?> = _detailMovie

    fun getDetailMovie(
        token:String,
        movieId : Int,
        language : String) = viewModelScope.launch {
            _detailMovie.value = Resource.Loading()
        movieUseCase.detailMovie(token, movieId, language).collectLatest {
            _detailMovie.value = it
        }
    }



}