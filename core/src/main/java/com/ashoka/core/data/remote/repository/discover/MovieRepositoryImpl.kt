package com.ashoka.core.data.remote.repository.discover

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.resource.RemoteDataSource
import com.ashoka.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository{
    override suspend fun discoverMovie(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>> =
        remoteDataSource.getDiscoverMovie(token, adultStatus, videoStatus, language, sortBy)
}