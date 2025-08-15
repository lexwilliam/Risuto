package com.lexwilliam.data.di

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.CharacterRemoteSource
import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.HistoryLocalSource
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.PersonRemoteSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.mapper.AnimeMapper
import com.lexwilliam.data.mapper.AnimeMapperImpl
import com.lexwilliam.data.mapper.CharacterMapper
import com.lexwilliam.data.mapper.CharacterMapperImpl
import com.lexwilliam.data.mapper.DetailMapper
import com.lexwilliam.data.mapper.DetailMapperImpl
import com.lexwilliam.data.mapper.HistoryMapper
import com.lexwilliam.data.mapper.HistoryMapperImpl
import com.lexwilliam.data.mapper.PersonMapper
import com.lexwilliam.data.mapper.PersonMapperImpl
import com.lexwilliam.data.mapper.UserMapper
import com.lexwilliam.data.mapper.UserMapperImpl
import com.lexwilliam.data.repository.AnimeRepositoryImpl
import com.lexwilliam.data.repository.CharacterRepositoryImpl
import com.lexwilliam.data.repository.DetailRepositoryImpl
import com.lexwilliam.data.repository.HistoryRepositoryImpl
import com.lexwilliam.data.repository.OAuthRepositoryImpl
import com.lexwilliam.data.repository.PersonRepositoryImpl
import com.lexwilliam.data.repository.UserRepositoryImpl
import com.lexwilliam.domain.repository.AnimeRepository
import com.lexwilliam.domain.repository.CharacterRepository
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.repository.HistoryRepository
import com.lexwilliam.domain.repository.OAuthRepository
import com.lexwilliam.domain.repository.PersonRepository
import com.lexwilliam.domain.repository.UserRepository
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