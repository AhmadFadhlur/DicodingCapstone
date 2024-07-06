package com.ashoka.core.data.remote.paging.discover

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashoka.core.data.remote.network.ApiDiscoverMovieService
import com.ashoka.core.data.remote.response.ResultMovieItem

class MovieDiscoverPagingSource(
    private val apiDiscoverMovieService: ApiDiscoverMovieService,
    private val token : String,
    private val adultStatus : Boolean,
    private val videoStatus : Boolean,
    private val language : String,
    private val sortBy : String
) : PagingSource<Int, ResultMovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResultMovieItem>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            Log.i("getRefreshKeySearch ", "anchorPosition = $anchorPosition")

            val anchorPage =state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultMovieItem>  =
        try {
            val page  = params.key ?: INITIAL_PAGE_INDEX
            val movieDiscover =apiDiscoverMovieService.discoverMovie(
                token, adultStatus, videoStatus, language, page, sortBy).results!!
            Log.i("loadSearch ", "movieDiscover = $movieDiscover")
            LoadResult.Page(
                data = movieDiscover,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if(movieDiscover.isEmpty()) null else page + 1
            )
        } catch (e : Exception){
            LoadResult.Error(e)
        }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}