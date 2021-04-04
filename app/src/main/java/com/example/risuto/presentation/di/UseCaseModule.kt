package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.example.risuto.data.repository.ItemRepository
import com.example.risuto.domain.model.SeasonAnime
import com.example.risuto.domain.usecase.GetAnimeUseCase
import com.example.risuto.domain.usecase.SeasonAnimeUseCase
import com.example.risuto.domain.usecase.TopAnimeUseCase
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
    fun provideAnime(itemRepository: ItemRepository): GetAnimeUseCase {
        return GetAnimeUseCase(itemRepository)
    }
}