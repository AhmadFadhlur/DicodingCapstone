package com.ashoka.core.domain.repository

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.ResultMovieItem
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun discoverMovie(
        token:String,
        adultStatus:Boolean,
        videoStatus:Boolean,
        language:String,
        sortBy : String) : Flow<PagingData<ResultMovieItem>>
}