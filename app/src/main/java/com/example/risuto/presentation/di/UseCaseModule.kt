package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.repository.ItemRepository
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.example.risuto.domain.usecase.GetTopResultUseCase
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
    fun provideSearchAnimeUseCase(itemRepository: ItemRepository): SearchAnimeUseCase {
        return SearchAnimeUseCase(itemRepository)
    }

    @Provides
    @Singleton
    fun provideGetTopResultUseCase(itemRepository: ItemRepository): GetTopResultUseCase {
        return GetTopResultUseCase(itemRepository)
    }
}