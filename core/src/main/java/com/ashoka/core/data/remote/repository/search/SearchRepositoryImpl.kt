package com.ashoka.core.data.remote.repository.search


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashoka.core.data.remote.network.ApiSearchMoviesService
import com.ashoka.core.data.remote.paging.MovieSearchPagingSource
import com.ashoka.core.data.remote.response.ResultMovieItem
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
    ): Flow<PagingData<ResultMovieItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { MovieSearchPagingSource(
            apiSearchMovies,token,q,adultStatus,language) }
    ).flow


}