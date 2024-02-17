package com.ashoka.core.di

import com.ashoka.core.data.remote.repository.detail.DetailRepository
import com.ashoka.core.data.remote.repository.detail.DetailRepositoryImpl
import com.ashoka.core.data.remote.repository.discover.DiscoverRepository
import com.ashoka.core.data.remote.repository.discover.DiscoverRepositoryImpl
import com.ashoka.core.data.remote.repository.search.SearchRepository
import com.ashoka.core.data.remote.repository.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    abstract fun bindDetailRepository(detailRepositoryImpl: DetailRepositoryImpl) : DetailRepository
//
//    @Binds
//    abstract fun bindDiscoverRepository(discoverRepositoryImpl: DiscoverRepositoryImpl) : DiscoverRepository
//
//    @Binds
//    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl) : SearchRepository
//}