package com.lexwilliam.data_remote.di

import com.lexwilliam.data.*
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.data.*
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
        AnimeRemoteSourceImpl(jikanService,  animeMapper)

    @Singleton
    @Provides
    fun provideDetailRemoteSource(
        malService: MyAnimeListService,
        jikanService: JikanService,
        detailMapper: DetailMapper
    ): DetailRemoteSource =
        DetailRemoteSourceImpl(malService, jikanService, detailMapper)

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
        jikanService: JikanService,
        animeMapper: AnimeMapper,
        userMapper: UserMapper
    ): UserRemoteSource =
        UserRemoteSourceImpl(malService, jikanService, animeMapper, userMapper)

    @Singleton
    @Provides
    fun providePersonRemoteSource(
        jikanService: JikanService,
        personMapper: PersonMapper
    ): PersonRemoteSource =
        PersonRemoteSourceImpl(jikanService, personMapper)

    @Singleton
    @Provides
    fun provideAnimeMapper(): AnimeMapper = AnimeMapperImpl()

    @Singleton
    @Provides
    fun provideDetailMapper(): DetailMapper = DetailMapperImpl()

    @Singleton
    @Provides
    fun provideOAuthMapper(): OAuthMapper = OAuthMapperImpl()

    @Singleton
    @Provides
    fun provideUserMapper(): UserMapper = UserMapperImpl()

    @Singleton
    @Provides
    fun providePersonMapper(): PersonMapper = PersonMapperImpl()
}