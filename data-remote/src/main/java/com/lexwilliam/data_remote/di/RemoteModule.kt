package com.lexwilliam.data_remote.di

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.CharacterRemoteSource
import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.PersonRemoteSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.data.AnimeRemoteSourceImpl
import com.lexwilliam.data_remote.data.CharacterRemoteSourceImpl
import com.lexwilliam.data_remote.data.DetailRemoteSourceImpl
import com.lexwilliam.data_remote.data.OAuthRemoteSourceImpl
import com.lexwilliam.data_remote.data.PersonRemoteSourceImpl
import com.lexwilliam.data_remote.data.UserRemoteSourceImpl
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.AnimeMapperImpl
import com.lexwilliam.data_remote.mapper.CharacterMapper
import com.lexwilliam.data_remote.mapper.CharacterMapperImpl
import com.lexwilliam.data_remote.mapper.DetailMapper
import com.lexwilliam.data_remote.mapper.DetailMapperImpl
import com.lexwilliam.data_remote.mapper.OAuthMapper
import com.lexwilliam.data_remote.mapper.OAuthMapperImpl
import com.lexwilliam.data_remote.mapper.PersonMapper
import com.lexwilliam.data_remote.mapper.PersonMapperImpl
import com.lexwilliam.data_remote.mapper.UserMapper
import com.lexwilliam.data_remote.mapper.UserMapperImpl
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
    fun provideCharacterRemoteSource(
        jikanService: JikanService,
        characterMapper: CharacterMapper
    ): CharacterRemoteSource =
        CharacterRemoteSourceImpl(jikanService, characterMapper)

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

    @Singleton
    @Provides
    fun provideCharacterMapper(): CharacterMapper = CharacterMapperImpl()
}