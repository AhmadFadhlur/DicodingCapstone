package com.ashoka.core.di

import com.ashoka.core.data.remote.repository.discover.MovieRepositoryImpl
import com.ashoka.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl) : IMovieRepository

}