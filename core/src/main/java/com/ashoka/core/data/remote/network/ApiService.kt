package com.ashoka.core.data.remote.network

import com.ashoka.core.data.remote.response.detail.MovieDetailResponse
import com.ashoka.core.data.remote.response.detail.TvShowsDetailResponse
import com.ashoka.core.data.remote.response.discover.MovieDiscoverResponse
import com.ashoka.core.data.remote.response.discover.TvShowsResponse
import com.ashoka.core.data.remote.response.search.SearchResponse
import com.ashoka.core.data.remote.response.search.SearchTvshowsResponse
import com.ashoka.core.utils.EndPointMovie.DETAIL_MOVIE
import com.ashoka.core.utils.EndPointMovie.DETAIL_TV
import com.ashoka.core.utils.EndPointMovie.MOVIE
import com.ashoka.core.utils.EndPointMovie.SEARCH_MOVIE
import com.ashoka.core.utils.EndPointMovie.SEARCH_TVSHOWS
import com.ashoka.core.utils.EndPointMovie.TV
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

    @GET(TV)
    suspend fun discoverTvShow(
        @Header("Authorization") token : String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("include_video") videoStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String
    ) : TvShowsResponse
}

interface ApiDetailMoviesService{
    @GET(DETAIL_MOVIE)
    suspend fun detailMovies(
        @Header("Authorization") token : String,
        @Path("movie_id") movieId: Int,
        @Query("language") language : String,
    ) : MovieDetailResponse

    @GET(DETAIL_TV)
    suspend fun detailTvshows(
        @Header("Authorization") token : String,
        @Path("series_id") seriesId: Int,
        @Query("language") language : String,
    ) : TvShowsDetailResponse
}

interface ApiSearchMoviesService{
    @GET(SEARCH_MOVIE)
    suspend fun searchMovies(
        @Header("Authorization") token : String,
        @Query("query") q: String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int
    ) : SearchResponse

    @GET(SEARCH_TVSHOWS)
    suspend fun searchTvshows(
        @Header("Authorization") token : String,
        @Query("query") q: String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int
    ) : SearchTvshowsResponse

}

