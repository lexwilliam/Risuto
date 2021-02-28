package com.chun2maru.risutomvvm.presentation.di

import com.chun2maru.risutomvvm.data.repository.SearchRepository
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchAnimeUseCase(searchRepository: SearchRepository): SearchAnimeUseCase {
        return SearchAnimeUseCase(searchRepository)
    }
}