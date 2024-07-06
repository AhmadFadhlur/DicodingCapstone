package com.ashoka.core.domain.usecase

import androidx.paging.PagingData
import com.ashoka.core.data.remote.response.ResultMovieItem
import com.ashoka.core.data.remote.response.discover.ResultsTvShowItem
import com.ashoka.core.data.remote.response.search.ResultsSearchItem
import com.ashoka.core.data.remote.response.search.ResultsSearchTvShowsItem
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: IMovieRepository
) : MovieUseCase{
    override suspend fun getMovieDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy : String
    ): Flow<PagingData<ResultMovieItem>> =
        movieRepository.discoverMovie(token, adultStatus, videoStatus, language, sortBy)

    override suspend fun getTvShowDiscover(
        token: String,
        adultStatus: Boolean,
        videoStatus: Boolean,
        language: String,
        sortBy: String
    ): Flow<PagingData<ResultsTvShowItem>> =
        movieRepository.tvShowDiscover(token, adultStatus, videoStatus, language, sortBy)

    override suspend fun getSearchMovie(
        token: String,
        q: String,
        adultStatus: Boolean,
        language: String,
    ): Flow<PagingData<ResultsSearchItem>> =
        movieRepository.searchMovie(token, q, adultStatus, language)

    override suspend fun getSearchTvshows(
        token: String,
        q: String,
        adultStatus: Boolean,
        language: String
    ): Flow<PagingData<ResultsSearchTvShowsItem>> =
        movieRepository.searchTvshows(token, q, adultStatus, language)


    override suspend fun detailMovie(
        token: String,
        movieId: Int,
        language: String
    ): Flow<Resource<DetailMovie>>  =
        movieRepository.detailMovie(token, movieId, language)

    override suspend fun detailTvshows(
        token: String,
        movieId: Int,
        language: String
    ): Flow<Resource<DetailMovie>> =
        movieRepository.detailTvshows(token, movieId, language)

    override  fun getMoviesFavorite(): Flow<List<Movie>> =
        movieRepository.getMoviesFavorite()

    override  fun getMovieFavoriteById(id: Int): Flow<Boolean> =
        movieRepository.getMovieFavoriteById(id)

    override suspend fun insertMovie(movie: Movie) =
        movieRepository.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) =
        movieRepository.deleteMovie(movie)

}