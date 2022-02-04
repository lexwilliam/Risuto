package com.lexwilliam.risuto.di

import com.lexwilliam.risuto.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun provideMyAnimeMapper(): MyAnimeMapper = MyAnimeMapperImpl()

    @Singleton
    @Provides
    fun provideHistoryMapper(): HistoryMapper = HistoryMapperImpl()

    @Singleton
    @Provides
    fun provideCommonMapper(): CommonMapper = CommonMapperImpl()

    @Singleton
    @Provides
    fun provideAnimeMapper(commonMapper: CommonMapper): AnimeMapper = AnimeMapperImpl(commonMapper)

    @Singleton
    @Provides
    fun provideDetailMapper(commonMapper: CommonMapper): DetailMapper = DetailMapperImpl(commonMapper)

}