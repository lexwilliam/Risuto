package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.example.risuto.data.remote.model.detail.Genre
import com.example.risuto.data.repository.ItemRepository
import com.example.risuto.domain.model.SeasonAnime
import com.example.risuto.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchAnimeUseCase(listRepository: ListRepository): SearchAnimeUseCase {
        return SearchAnimeUseCase(listRepository)
    }

    @Provides
    @Singleton
    fun provideTopAnimeUseCase(listRepository: ListRepository): TopAnimeUseCase {
        return TopAnimeUseCase(listRepository)
    }

    @Provides
    @Singleton
    fun provideSeasonAnimeUseCase(listRepository: ListRepository): SeasonAnimeUseCase {
        return SeasonAnimeUseCase(listRepository)
    }

    @Provides
    @Singleton
    fun provideSeasonArchiveUseCase(itemRepository: ItemRepository): GetSeasonArchiveUseCase {
        return GetSeasonArchiveUseCase(itemRepository)
    }

    @Provides
    @Singleton
    fun provideAnime(itemRepository: ItemRepository): GetAnimeUseCase {
        return GetAnimeUseCase(itemRepository)
    }

    @Provides
    @Singleton
    fun provideCharacterStaffUseCase(itemRepository: ItemRepository): GetCharacterStaffUseCase {
        return GetCharacterStaffUseCase(itemRepository)
    }
}