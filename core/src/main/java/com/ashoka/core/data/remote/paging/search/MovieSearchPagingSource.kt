package com.ashoka.core.data.remote.paging.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashoka.core.data.remote.network.ApiSearchMoviesService
import com.ashoka.core.data.remote.response.search.ResultsSearchItem

class MovieSearchPagingSource (
    private val apiSearchMoviesService: ApiSearchMoviesService,
    private val token : String,
    private val q : String,
    private val adultStatus : Boolean,
    private val language : String
) : PagingSource<Int, ResultsSearchItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsSearchItem>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            Log.i("getRefreshKeySearch ", "anchorPosition $anchorPosition")
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsSearchItem> =
        try {
            val page  = params.key ?: INITIAL_PAGE_INDEX
            val movieSearch =apiSearchMoviesService.searchMovies(token, q, adultStatus, language, page).results!!
            Log.i("loadSearch ", "movieSearch = $movieSearch")
            LoadResult.Page(
                data = movieSearch,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if(movieSearch.isEmpty()) null else page + 1
            )

        }  catch (e : Exception){
            Log.i("loadSearch catch", "movieSearch = $e")
            LoadResult.Error(e)
        }

    //    override fun getRefreshKey(state: PagingState<Int, ResultMovieItem>): Int?  = state.anchorPosition?.let { anchorPosition ->
//        val anchorPage =state.closestPageToPosition(anchorPosition)
//        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsSearchItem> =
//        try {
//            val page  = params.key ?: INITIAL_PAGE_INDEX
//            val movieSearch =apiSearchMoviesService.searchMovies(token, q, adultStatus, language, page).results!!
//            LoadResult.Page(
//                data = movieSearch,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if(movieSearch.isEmpty()) null else page + 1
//            )
//        }  catch (e : Exception){
//            LoadResult.Error(e)
//        }
}