package com.ashoka.capstonedicoding.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private var _detailTvshows = MutableStateFlow<Resource<DetailMovie>?>(null)
    val detailTvshows : MutableStateFlow<Resource<DetailMovie>?> = _detailTvshows


    fun getDetailTvshows(
        token:String,
        movieId : Int,
        language : String
    ) = viewModelScope.launch {
        Log.i("tvShows" , "masuk vmTvShow")
        _detailTvshows.value = Resource.Loading()
        movieUseCase.detailTvshows(token, movieId, language).collectLatest {
            _detailTvshows.value = it
        }
    }

    fun getDetailMovie(
        token:String,
        movieId : Int,
        language : String
        ) = viewModelScope.launch {
            _detailMovie.value = Resource.Loading()
        movieUseCase.detailMovie(token, movieId, language).collectLatest {
            _detailMovie.value = it
        }
    }

    fun getFavMovieById(id : Int) : LiveData<Boolean> {
        Log.d("DetailMoviesViewModel", "getFavMovieById")
        return movieUseCase.getMovieFavoriteById(id).asLiveData()
    }


    fun insertFavMovie(movie:Movie) = viewModelScope.launch {
        Log.d("DetailMoviesViewModel", "INSERT. ${movie.title}")
        movieUseCase.insertMovie(movie)
    }

    fun deleteFavMovie(movie: Movie) = viewModelScope.launch {
        Log.d("DetailMoviesViewModel", "masuk DELETE")
        movieUseCase.deleteMovie(movie)
    }



}