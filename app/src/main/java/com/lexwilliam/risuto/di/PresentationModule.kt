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
    fun provideDetailMapper(): DetailMapper = DetailMapperImpl()

    @Singleton
    @Provides
    fun provideHistoryMapper(): HistoryMapper = HistoryMapperImpl()

    @Singleton
    @Provides
    fun provideAnimeMapper(): AnimeMapper = AnimeMapperImpl()

    @Singleton
    @Provides
    fun provideUserMapper(): UserMapper = UserMapperImpl()

    @Singleton
    @Provides
    fun providePersonMapper(): PersonMapper = PersonMapperImpl()
}