package com.lexwilliam.data_local.di

import com.lexwilliam.data_local.mapper.HistoryMapper
import com.lexwilliam.data_local.mapper.HistoryMapperImpl
import com.lexwilliam.data_local.mapper.MyAnimeMapper
import com.lexwilliam.data_local.mapper.MyAnimeMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideMyAnimeMapper(): MyAnimeMapper = MyAnimeMapperImpl()

    @Singleton
    @Provides
    fun provideHistoryMapper(): HistoryMapper = HistoryMapperImpl()
}