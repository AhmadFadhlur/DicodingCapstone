package com.ashoka.core.data.remote.network

import com.ashoka.core.data.remote.response.MovieDetailResponse
import com.ashoka.core.data.remote.response.MovieDiscoverResponse
import com.ashoka.core.data.remote.response.MovieSearchResponse
import com.ashoka.core.utils.EndPointMovie.DETAIL_MOVIE
import com.ashoka.core.utils.EndPointMovie.MOVIE
import com.ashoka.core.utils.EndPointMovie.SEARCH_MOVIE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDiscoverMovieService {
    @GET(MOVIE)
    suspend fun discoverMovie(
        @Header("Authorization") token : String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("include_video") videoStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String
    ) : MovieDiscoverResponse
}

interface ApiDetailMoviesService{
    @GET(DETAIL_MOVIE)
    suspend fun detailMovies(
        @Header("Authorization") token : String,
        @Path("movie_id") movieId: Int,
        @Query("language") language : String,
    ) : MovieDetailResponse
}

interface ApiSearchMoviesService{
    @GET(SEARCH_MOVIE)
    suspend fun searchMovies(
        @Header("Authorization") token : String,
        @Path("query") q: String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int
    ) : MovieSearchResponse

}

