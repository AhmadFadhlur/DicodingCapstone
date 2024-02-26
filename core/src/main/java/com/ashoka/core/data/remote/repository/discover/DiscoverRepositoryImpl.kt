package com.ashoka.core.data.remote.repository.discover

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashoka.core.data.remote.network.ApiDiscoverMovieService
import com.ashoka.core.data.remote.paging.MovieDiscoverPagingSource
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
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { MovieDiscoverPagingSource(
            apiDiscoverMovieService,token,adultStatus,videoStatus,language,sortBy) }
    ).flow
}