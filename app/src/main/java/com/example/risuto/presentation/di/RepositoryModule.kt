package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.remote.JikanService
import com.chun2maru.risutomvvm.data.repository.ItemRepository
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
    fun provideSearchRepository(jikanService: JikanService): ItemRepository{
        return ItemRepository(jikanService)
    }
}