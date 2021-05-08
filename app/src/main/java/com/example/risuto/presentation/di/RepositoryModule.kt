package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.remote.JikanService
import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.example.risuto.data.local.dao.HistoryDao
import com.example.risuto.data.local.repository.HistoryRepository
import com.example.risuto.data.remote.repository.ItemRepository
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
    fun provideListRepository(jikanService: JikanService): ListRepository{
        return ListRepository(jikanService)
    }

    @Singleton
    @Provides
    fun provideItemRepository(jikanService: JikanService): ItemRepository{
        return ItemRepository(jikanService)
    }

    @Singleton
    @Provides
    fun provideHistoryRepository(historyDao: HistoryDao): HistoryRepository{
        return HistoryRepository(historyDao)
    }
}