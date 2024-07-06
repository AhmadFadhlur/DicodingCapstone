package com.ashoka.core.domain.usecase

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.remote.response.discover.ResultsTvShowItem
import com.ashoka.core.data.remote.response.search.ResultsSearchItem
import com.ashoka.core.data.remote.response.search.ResultsSearchTvShowsItem
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getMovieDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>>

    suspend fun getTvShowDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultsTvShowItem>>

    suspend fun getSearchMovie (
        token: String,
        q : String,
        adultStatus: Boolean,
        language: String,
    ) : Flow<PagingData<ResultsSearchItem>>
    suspend fun getSearchTvshows (
        token: String,
        q : String,
        adultStatus: Boolean,
        language: String,
    ) : Flow<PagingData<ResultsSearchTvShowsItem>>

    suspend fun detailMovie (
        token:String,
        movieId : Int,
        language : String
    ): Flow<Resource<DetailMovie>>

    suspend fun detailTvshows (
        token:String,
        movieId : Int,
        language : String
    ): Flow<Resource<DetailMovie>>

     fun getMoviesFavorite(): Flow<List<Movie>>
     fun getMovieFavoriteById(id: Int): Flow<Boolean>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}