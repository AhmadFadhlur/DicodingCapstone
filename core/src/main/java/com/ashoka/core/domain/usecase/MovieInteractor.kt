package com.ashoka.core.domain.usecase

import androidx.paging.PagingData
import com.ashoka.core.data.remote.repository.discover.MovieRepositoryImpl
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: IMovieRepository
) : MovieUseCase{
    override suspend fun getMovieDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>> =
        movieRepository.discoverMovie(token, adultStatus, videoStatus, language, sortBy)
}