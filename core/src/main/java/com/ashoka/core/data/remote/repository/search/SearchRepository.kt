package com.ashoka.core.data.remote.repository.search


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.ashoka.core.data.remote.response.ResultMovieItem

interface SearchRepository {
    suspend fun searchMovie(
        token:String,
        q : String,
        adultStatus : Boolean,
        language : String
    ) : Flow<PagingData<ResultMovieItem>>
}