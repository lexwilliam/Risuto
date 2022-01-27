package com.lexwilliam.data.di

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.HistoryLocalSource
import com.lexwilliam.data.MyAnimeLocalSource
import com.lexwilliam.data.mapper.*
import com.lexwilliam.data.repository.AnimeRepositoryImpl
import com.lexwilliam.data.repository.DetailRepositoryImpl
import com.lexwilliam.data.repository.HistoryRepositoryImpl
import com.lexwilliam.data.repository.MyAnimeRepositoryImpl
import com.lexwilliam.domain.repository.AnimeRepository
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.repository.HistoryRepository
import com.lexwilliam.domain.repository.MyAnimeRepository
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
    fun provideMyAnimeRepository(
        myAnimeLocalSource: MyAnimeLocalSource,
        myAnimeMapper: MyAnimeMapper
    ): MyAnimeRepository =
        MyAnimeRepositoryImpl(myAnimeLocalSource, myAnimeMapper)

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