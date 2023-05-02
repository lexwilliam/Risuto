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
        oAuthLocalSource: OAuthLocalSource,
        detailMapper: DetailMapper
    ): DetailRepository =
        DetailRepositoryImpl(detailRemoteSource, oAuthLocalSource, detailMapper)

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
        oAuthLocalSource: OAuthLocalSource,
        animeMapper: AnimeMapper,
        userMapper: UserMapper
    ): UserRepository =
        UserRepositoryImpl(userRemoteSource, oAuthLocalSource, animeMapper, userMapper)

    @Singleton
    @Provides
    fun providePersonRepository(
        personRemoteSource: PersonRemoteSource,
        personMapper: PersonMapper
    ): PersonRepository =
        PersonRepositoryImpl(personRemoteSource, personMapper)

    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterRemoteSource: CharacterRemoteSource,
        characterMapper: CharacterMapper
    ): CharacterRepository =
        CharacterRepositoryImpl(characterRemoteSource, characterMapper)

    @Singleton
    @Provides
    fun provideHistoryMapper(): HistoryMapper = HistoryMapperImpl()

    @Singleton
    @Provides
    fun provideAnimeMapper(): AnimeMapper = AnimeMapperImpl()

    @Singleton
    @Provides
    fun provideDetailMapper(): DetailMapper = DetailMapperImpl()

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