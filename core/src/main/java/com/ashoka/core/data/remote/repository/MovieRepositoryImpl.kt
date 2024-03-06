package com.ashoka.core.data.remote.repository

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.resource.ApiSourceResponse
import com.ashoka.core.data.resource.NetworkBoundResource
import com.ashoka.core.data.remote.RemoteDataSource
import com.ashoka.core.data.remote.RemoteLocalSource
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.domain.repository.IMovieRepository
import com.ashoka.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: RemoteLocalSource
) : IMovieRepository{
    override suspend fun discoverMovie(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>> =
        remoteDataSource.getDiscoverMovie(token, adultStatus, videoStatus, language, sortBy)

    override suspend fun detailMovie(
        token: String,
        movieId: Int,
        language: String
    ): Flow<Resource<DetailMovie>> =  object : NetworkBoundResource<DetailMovie, MovieDetailResponse>(){
        override fun loadFromNetwork(data: MovieDetailResponse): Flow<DetailMovie> =
            DataMapper.mapDetailResponseToDomain(data)

        override suspend fun createCall(): Flow<ApiSourceResponse<MovieDetailResponse>> =
            remoteDataSource.getDetailMovie(token, movieId, language)

    }.asFlow()

    override fun getMoviesFavorite(): Flow<List<Movie>> =
        localDataSource.getMoviesFavorite().map {
            DataMapper.mapListEntityFavToDomain(it)
        }

     override fun getMovieFavoriteById(id: Int): Flow<Boolean> =
         localDataSource.getMovieFavoriteById(id)

    override suspend fun insertMovie(movie: Movie) =
        localDataSource.insertMovie(DataMapper.mapMovieDomainToEntity(movie))

    override suspend fun deleteMovie(movie: Movie) =
        localDataSource.deleteMovie(DataMapper.mapMovieDomainToEntity(movie))

}