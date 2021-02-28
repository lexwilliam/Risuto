package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.remote.AnimeService
import com.chun2maru.risutomvvm.data.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(animeService: AnimeService): SearchRepository{
        return SearchRepository(animeService)
    }
}