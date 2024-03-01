package com.ashoka.core.domain.usecase

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getMovieDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>>


    suspend fun detailMovie (
        token:String,
        movieId : Int,
        language : String
    ): Flow<Resource<DetailMovie>>
}