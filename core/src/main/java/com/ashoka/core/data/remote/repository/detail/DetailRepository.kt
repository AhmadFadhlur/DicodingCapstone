package com.ashoka.core.data.remote.repository.detail

import com.ashoka.core.data.resource.Resource
import com.ashoka.core.data.remote.response.MovieDiscoverResponse
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun dicoverMovie (
        token:String,
        movieId : Int,
        language : String
    ): Flow<Resource<MovieDiscoverResponse>>
}