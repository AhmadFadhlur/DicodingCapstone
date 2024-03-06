package com.ashoka.capstonedicoding.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ashoka.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel(){
    val favMovie = useCase.getMoviesFavorite().asLiveData()

}