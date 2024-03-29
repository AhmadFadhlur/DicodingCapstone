package com.ashoka.core.domain.repository

import androidx.paging.PagingData
import com.ashoka.core.data.local.entities.FavoriteMovieEntity
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.MovieDiscoverResponse
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun discoverMovie(
        token:String,
        adultStatus:Boolean,
        videoStatus:Boolean,
        language:String,
        sortBy : String) : Flow<PagingData<ResultMovieItem>>
    suspend fun detailMovie (
        token:String,
        movieId : Int,
        language : String
    ): Flow<Resource<DetailMovie>>
    fun getMoviesFavorite(): Flow<List<Movie>>
    fun getMovieFavoriteById(id: Int): Flow<Boolean>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}