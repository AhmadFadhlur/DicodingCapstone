package com.ashoka.core.di

import com.ashoka.core.data.remote.network.ApiConfig
import com.ashoka.core.data.remote.network.ApiDetailMoviesService
import com.ashoka.core.data.remote.network.ApiDiscoverMovieService
import com.ashoka.core.data.remote.network.ApiSearchMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideDiscoverMovieApiService () :ApiDiscoverMovieService =ApiConfig.getDiscoverApiService()

    @Singleton
    @Provides
    fun provideDetailMovieApiService() : ApiDetailMoviesService = ApiConfig.getDetailApiService()

    @Singleton
    @Provides
    fun provideSearchApiService() : ApiSearchMoviesService = ApiConfig.getMovieSearchApiService()
}