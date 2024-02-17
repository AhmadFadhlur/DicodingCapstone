package com.ashoka.core.data.remote.repository.search


import com.ashoka.core.data.Resource
import com.ashoka.core.data.remote.network.ApiSearchMoviesService
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.MovieSearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiSearchMovies: ApiSearchMoviesService
) : SearchRepository {
    override suspend fun searchMovie(
        token: String,
        q: String,
        adultStatus: Boolean,
        language: String
    ): Flow<Resource<MovieSearchResponse>> {
        TODO("Not yet implemented")
    }


}