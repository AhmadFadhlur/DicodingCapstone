package com.ashoka.core.data.remote.repository.search


import kotlinx.coroutines.flow.Flow
import com.ashoka.core.data.Resource
import com.ashoka.core.data.remote.response.MovieSearchResponse

interface SearchRepository {
    suspend fun searchMovie(
        token:String,
        q : String,
        adultStatus : Boolean,
        language : String
    ) : Flow<Resource<MovieSearchResponse>>
}