package com.lexwilliam.data.di

import com.lexwilliam.data.*
import com.lexwilliam.data.mapper.*
import com.lexwilliam.data.repository.*
import com.lexwilliam.domain.repository.*
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
    fun provideHistoryRepository(
        historyLocalSource: HistoryLocalSource,
        historyMapper: HistoryMapper
    ): HistoryRepository =
        HistoryRepositoryImpl(historyLocalSource, historyMapper)

    @Singleton
    @Provides
    fun provideAnimeRepository(
        animeRemoteSource: AnimeRemoteSource,
        animeMapper: AnimeMapper
    ): AnimeRepository =
        AnimeRepositoryImpl(animeRemoteSource, animeMapper)

    @Singleton
    @Provides
    fun provideDetailRepository(
        detailRemoteSource: DetailRemoteSource,
        detailMapper: DetailMapper
    ): DetailRepository =
        DetailRepositoryImpl(detailRemoteSource, detailMapper)

    @Singleton
    @Provides
    fun provideOAuthRepository(
        oAuthRemoteSource: OAuthRemoteSource,
        oAuthLocalSource: OAuthLocalSource
    ): OAuthRepository =
        OAuthRepositoryImpl(oAuthRemoteSource, oAuthLocalSource)

    @Singleton
    @Provides
    fun provideUserRepository(
        userRemoteSource: UserRemoteSource,
        animeMapper: AnimeMapper
    ): UserRepository =
        UserRepositoryImpl(userRemoteSource, animeMapper)

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