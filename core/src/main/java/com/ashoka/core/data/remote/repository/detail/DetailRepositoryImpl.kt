package com.ashoka.core.data.remote.repository.detail

import com.ashoka.core.data.resource.Resource
import com.ashoka.core.data.remote.network.ApiDetailMoviesService
import com.ashoka.core.data.remote.response.MovieDiscoverResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DetailRepositoryImpl @Inject constructor(
    private val apiDetailMovies: ApiDetailMoviesService
) : DetailRepository{
    override suspend fun dicoverMovie(
        token: String,
        movieId: Int,
        language: String
    ): Flow<Resource<MovieDiscoverResponse>> {
        TODO("Not yet implemented")
    }

}