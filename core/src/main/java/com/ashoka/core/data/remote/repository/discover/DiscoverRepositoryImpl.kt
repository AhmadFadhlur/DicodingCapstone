package com.ashoka.core.data.remote.repository.discover

import androidx.paging.PagingData
import com.ashoka.core.data.remote.network.ApiDiscoverMovieService
import com.ashoka.core.data.remote.response.ResultMovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val apiDiscoverMovieService: ApiDiscoverMovieService
) : DiscoverRepository{
    override suspend fun discoverMovie(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String
    ): Flow<PagingData<ResultMovieItem>> {
        TODO("Not yet implemented")
    }
}