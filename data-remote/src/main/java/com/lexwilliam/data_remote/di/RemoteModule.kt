package com.lexwilliam.data_remote.di

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.data.AnimeRemoteSourceImpl
import com.lexwilliam.data_remote.data.DetailRemoteSourceImpl
import com.lexwilliam.data_remote.data.OAuthRemoteSourceImpl
import com.lexwilliam.data_remote.data.UserRemoteSourceImpl
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
    fun provideAnimeRemoteSource(
        jikanService: JikanService,
        animeMapper: AnimeMapper
    ): AnimeRemoteSource =
        AnimeRemoteSourceImpl(jikanService, animeMapper)

    @Singleton
    @Provides
    fun provideDetailRemoteSource(
        jikanService: JikanService,
        detailMapper: DetailMapper
    ): DetailRemoteSource =
        DetailRemoteSourceImpl(jikanService, detailMapper)

    @Singleton
    @Provides
    fun provideOAuthRemoteSource(
        malService: MyAnimeListService,
        oAuthMapper: OAuthMapper
    ): OAuthRemoteSource =
        OAuthRemoteSourceImpl(malService, oAuthMapper)

    @Singleton
    @Provides
    fun provideUserRemoteSource(
        malService: MyAnimeListService,
        animeMapper: AnimeMapper
    ): UserRemoteSource =
        UserRemoteSourceImpl(malService, animeMapper)

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

    @Singleton
    @Provides
    fun provideOAuthMapper(): OAuthMapper = OAuthMapperImpl()
}