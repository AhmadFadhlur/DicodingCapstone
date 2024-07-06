package com.ashoka.core.utils

import com.ashoka.core.data.local.entities.FavoriteMovieEntity
import com.ashoka.core.data.remote.response.detail.MovieDetailResponse
import com.ashoka.core.data.remote.response.detail.TvShowsDetailResponse
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.GenreMovie
import com.ashoka.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    fun mapDetailResponseToDomain(movieDetailResponse: MovieDetailResponse)
    : Flow<DetailMovie> = flowOf(
        DetailMovie(
            id = movieDetailResponse.id!!,
            backdropPath = movieDetailResponse.backdropPath!!,
            originalTitle = movieDetailResponse.originalTitle!!,
            title = movieDetailResponse.title!!,
            genres = movieDetailResponse.genres!!.map {
                GenreMovie(it!!.name!!, it.id!!)
            },
            popularity = movieDetailResponse.popularity!!.toString(),
            overview = movieDetailResponse.overview!!,
            posterPath = movieDetailResponse.posterPath!!,
            releaseDate = movieDetailResponse.releaseDate!!,
            voteAverage = movieDetailResponse.voteAverage!!.toString(),
            homepage = movieDetailResponse.homepage!!
        )
    )

    fun mapDetailResponseTvshowsToDomain(tvShowsDetailResponse: TvShowsDetailResponse)
    : Flow<DetailMovie> = flowOf(
        DetailMovie(
            id = tvShowsDetailResponse.id!!,
            backdropPath = tvShowsDetailResponse.backdropPath!!,
            originalTitle = tvShowsDetailResponse.originalName!!,
            title = tvShowsDetailResponse.name!!,
            genres = tvShowsDetailResponse.genres!!.map {
                GenreMovie(it!!.name!!, it.id!!)
            },
            popularity = tvShowsDetailResponse.popularity!!.toString(),
            overview = tvShowsDetailResponse.overview!!,
            posterPath = tvShowsDetailResponse.posterPath!!,
            releaseDate = tvShowsDetailResponse.firstAirDate!!,
            voteAverage = tvShowsDetailResponse.voteAverage!!.toString(),
            homepage = tvShowsDetailResponse.homepage!!
        )
    )

    fun mapDetailDomaintoMovieDomain(detailMovie: DetailMovie) : Movie =
        Movie(
            id = detailMovie.id,
            posterPath = detailMovie.posterPath,
            title = detailMovie.title,
            originalTitle = detailMovie.originalTitle,
            popularity = detailMovie.popularity,
            releaseDate = detailMovie.releaseDate
        )
    fun mapMovieDomainToEntity(movie: Movie)
    : FavoriteMovieEntity =
        FavoriteMovieEntity(
            id = movie.id,
            posterPath = movie.posterPath,
            title = movie.title,
            originalTitle = movie.originalTitle,
            popularity = movie.popularity,
            releaseDate = movie.releaseDate
        )

    fun mapListEntityFavToDomain(movie: List<FavoriteMovieEntity>) : List<Movie> {
        val listFavMovie = ArrayList<Movie>()
        movie.map {
            val movie = Movie(
                id = it.id,
                posterPath = it.posterPath,
                title = it.title,
                originalTitle = it.originalTitle,
                popularity = it.popularity,
                releaseDate = it.releaseDate
            )
            listFavMovie.add(movie)
        }
        return listFavMovie
    }
}