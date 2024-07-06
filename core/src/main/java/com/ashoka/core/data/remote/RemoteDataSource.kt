package com.ashoka.core.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashoka.core.data.remote.network.ApiDetailMoviesService
import com.ashoka.core.data.remote.network.ApiDiscoverMovieService
import com.ashoka.core.data.remote.network.ApiSearchMoviesService
import com.ashoka.core.data.remote.paging.discover.MovieDiscoverPagingSource
import com.ashoka.core.data.remote.paging.search.MovieSearchPagingSource
import com.ashoka.core.data.remote.paging.discover.TvShowDiscoverPagingSource
import com.ashoka.core.data.remote.paging.search.TvshowsSearchPagingSource
import com.ashoka.core.data.remote.response.detail.MovieDetailResponse
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.remote.response.detail.TvShowsDetailResponse
import com.ashoka.core.data.remote.response.discover.ResultsTvShowItem
import com.ashoka.core.data.remote.response.search.ResultsSearchItem
import com.ashoka.core.data.remote.response.search.ResultsSearchTvShowsItem
import com.ashoka.core.data.resource.ApiSourceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
        pagingSourceFactory = {
            Log.i("remote getDiscoverMovie", "masuk")
            MovieDiscoverPagingSource(
            apiDiscoverMovieService,token,adultStatus,videoStatus,language,sortBy) }
    ).flow

    suspend fun getTvShowDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ) : Flow<PagingData<ResultsTvShowItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { TvShowDiscoverPagingSource(
            apiDiscoverMovieService,token,adultStatus,videoStatus,language,sortBy) }
    ).flow


    suspend fun getDetailMovie(
         token: String,
         movieId: Int,
         language: String
     ) : Flow<ApiSourceResponse.Success<MovieDetailResponse>> = flow {
         try {
             val response = apiDetailMoviesService.detailMovies(token, movieId, language)
             emit(ApiSourceResponse.Success(response))
         } catch (e : Exception) {
             e.printStackTrace()
             Log.e("REMOTE_DATA_SOURCE", e.toString())
         }
    }.flowOn(Dispatchers.IO)

    suspend fun getDetailTv(
        token: String,
        seriesId: Int,
        language: String
    ) : Flow<ApiSourceResponse.Success<TvShowsDetailResponse>> = flow {
        try {
            val response = apiDetailMoviesService.detailTvshows(token,seriesId, language)
            Log.e("tvShows REMOTE_DATA_SOURCE", "$response")
            emit(ApiSourceResponse.Success(response))
        } catch (e : Exception) {
            e.printStackTrace()
            Log.e("tvShows REMOTE_DATA_SOURCE", e.toString())
        }
    }.flowOn(Dispatchers.IO)


    suspend fun getSearchMovie (
        token: String,
        q : String,
        adultStatus: Boolean,
        language: String,
    ) : Flow<PagingData<ResultsSearchItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = {
            Log.i("remote getSearchMovie", "masuk")
            MovieSearchPagingSource(
            apiSearchMoviesService,token,q,adultStatus,language) }
    ).flow

    suspend fun getSearchTvshows(
        token: String,
        q : String,
        adultStatus: Boolean,
        language: String,
    ) : Flow<PagingData<ResultsSearchTvShowsItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = {
            Log.i("remote getSearchMovie", "masuk")
            TvshowsSearchPagingSource(
                apiSearchMoviesService,token,q,adultStatus,language) }
    ).flow
}
