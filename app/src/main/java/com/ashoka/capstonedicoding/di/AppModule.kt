package com.ashoka.capstonedicoding.di

import com.ashoka.core.data.remote.repository.detail.DetailRepository
import com.ashoka.core.data.remote.repository.detail.DetailRepositoryImpl
import com.ashoka.core.data.remote.repository.discover.MovieRepositoryImpl
import com.ashoka.core.data.remote.repository.search.SearchRepository
import com.ashoka.core.data.remote.repository.search.SearchRepositoryImpl
import com.ashoka.core.domain.usecase.MovieInteractor
import com.ashoka.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor) : MovieUseCase
}