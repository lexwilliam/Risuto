package com.lexwilliam.data_remote.di

import com.lexwilliam.data_remote.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideCommonMapper(): CommonMapper = CommonMapperImpl()

    @Singleton
    @Provides
    fun provideAnimeMapper(commonMapper: CommonMapper): AnimeMapper = AnimeMapperImpl(commonMapper)

    @Singleton
    @Provides
    fun provideArchiveMapper(): ArchiveMapper = ArchiveMapperImpl()

    @Singleton
    @Provides
    fun provideDetailMapper(commonMapper: CommonMapper): DetailMapper = DetailMapperImpl(commonMapper)
}