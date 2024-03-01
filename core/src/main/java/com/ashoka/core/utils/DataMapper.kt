package com.ashoka.core.utils

import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.GenreMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataMapper {

    fun mapDetailResponseToDomain(movieDetailResponse: MovieDetailResponse)
    : Flow<DetailMovie> = flow {
        movieDetailResponse.let { movieDetail ->
                    DetailMovie(
                        id = movieDetail.id!!,
                        backdropPath = movieDetail.backdropPath!!,
                        originalTitle = movieDetail.originalTitle!!,
                        title = movieDetail.title!!,
                        genres = movieDetail.genres!!.map {
                            GenreMovie(it!!.name!!, it.id!!)
                        },
                        popularity = movieDetail.popularity!!,
                        overview = movieDetail.overview!!,
                        posterPath = movieDetail.posterPath!!,
                        releaseDate = movieDetail.releaseDate!!,
                        voteAverage = movieDetail.voteAverage!!,
                        homepage = movieDetail.homepage!!
                    )
        }
    }
}