package com.ashoka.core.data.resource

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ashoka.core.data.remote.network.ApiDetailMoviesService
import com.ashoka.core.data.remote.network.ApiDiscoverMovieService
import com.ashoka.core.data.remote.network.ApiSearchMoviesService
import com.ashoka.core.data.remote.paging.MovieDiscoverPagingSource
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.MovieDiscoverResponse
import com.ashoka.core.data.remote.response.ResultMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiDiscoverMovieService: ApiDiscoverMovieService,
    private val apiSearchMoviesService: ApiSearchMoviesService,
    private val apiDetailMoviesService: ApiDetailMoviesService
) {
    suspend fun getDiscoverMovie (
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ) : Flow<PagingData<ResultMovieItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { MovieDiscoverPagingSource(
            apiDiscoverMovieService,token,adultStatus,videoStatus,language,sortBy) }
    ).flow

    suspend fun getDetailMovie(
         token: String,
         movieId: Int,
         language: String
     ) : Flow<ApiSourceResponse<MovieDetailResponse>> = flow {
         try {
             val response = apiDetailMoviesService.detailMovies(token, movieId, language)
             emit(ApiSourceResponse.Success(response))
         } catch (e : Exception) {
             e.printStackTrace()
             Log.e("REMOTE_DATA_SOURCE", e.toString())
         }
    }
}
