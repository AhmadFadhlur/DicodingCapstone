package com.ashoka.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val movieUseCase: MovieUseCase) : ViewModel() {
    val favMovie = movieUseCase.getMoviesFavorite().asLiveData()

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        movieUseCase.deleteMovie(movie)
    }


}