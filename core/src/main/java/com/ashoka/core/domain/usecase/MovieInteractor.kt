package com.ashoka.core.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
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

    override suspend fun detailMovie(
        token: String,
        movieId: Int,
        language: String
    ): Flow<Resource<DetailMovie>>  =
        movieRepository.detailMovie(token, movieId, language)


}