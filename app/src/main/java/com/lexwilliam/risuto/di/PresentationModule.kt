package com.lexwilliam.risuto.di

import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.mapper.AnimeMapperImpl
import com.lexwilliam.risuto.mapper.CharacterMapper
import com.lexwilliam.risuto.mapper.CharacterMapperImpl
import com.lexwilliam.risuto.mapper.DetailMapper
import com.lexwilliam.risuto.mapper.DetailMapperImpl
import com.lexwilliam.risuto.mapper.HistoryMapper
import com.lexwilliam.risuto.mapper.HistoryMapperImpl
import com.lexwilliam.risuto.mapper.PersonMapper
import com.lexwilliam.risuto.mapper.PersonMapperImpl
import com.lexwilliam.risuto.mapper.UserMapper
import com.lexwilliam.risuto.mapper.UserMapperImpl
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

    @Singleton
    @Provides
    fun provideCharacterMapper(): CharacterMapper = CharacterMapperImpl()
}