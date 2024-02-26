package com.ashoka.core.data.remote.repository.discover

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.ResultMovieItem
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {

    suspend fun discoverMovie(
        token:String,
        adultStatus:Boolean,
        videoStatus:Boolean,
        language:String,
        sortBy : String) : Flow<PagingData<ResultMovieItem>>


}