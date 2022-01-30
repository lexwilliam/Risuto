package com.lexwilliam.domain.di

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.remote.season.SeasonAnime
import com.lexwilliam.domain.repository.AnimeRepository
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.repository.HistoryRepository
import com.lexwilliam.domain.repository.MyAnimeRepository
import com.lexwilliam.domain.usecase.local.*
import com.lexwilliam.domain.usecase.remote.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideDeleteAllAnimeHistory(historyRepository: HistoryRepository): DeleteAllAnimeHistory =
        DeleteAllAnimeHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideDeleteAllSearchHistory(historyRepository: HistoryRepository): DeleteAllSearchHistory =
        DeleteAllSearchHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideDeleteAnimeByTitle(historyRepository: HistoryRepository): DeleteAnimeByTitle =
        DeleteAnimeByTitleImpl(historyRepository)

    @Singleton
    @Provides
    fun provideDeleteMyAnime(myAnimeRepository: MyAnimeRepository): DeleteMyAnime =
        DeleteMyAnimeImpl(myAnimeRepository)

    @Singleton
    @Provides
    fun provideDeleteSearchHistory(historyRepository: HistoryRepository): DeleteSearchHistory =
        DeleteSearchHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideGetAnimeHistory(historyRepository: HistoryRepository): GetAnimeHistory =
        GetAnimeHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideGetMyAnimes(myAnimeRepository: MyAnimeRepository): GetMyAnimes =
        GetMyAnimesImpl(myAnimeRepository)

    @Singleton
    @Provides
    fun provideGetMyAnimeWithWatchStatus(myAnimeRepository: MyAnimeRepository): GetMyAnimeWithWatchStatus =
        GetMyAnimeWithWatchStatusImpl(myAnimeRepository)

    @Singleton
    @Provides
    fun provideGetSearchHistory(historyRepository: HistoryRepository): GetSearchHistory =
        GetSearchHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideInsertAnimeHistory(historyRepository: HistoryRepository): InsertAnimeHistory =
        InsertAnimeHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideInsertMyAnime(myAnimeRepository: MyAnimeRepository): InsertMyAnime =
        InsertMyAnimeImpl(myAnimeRepository)

    @Singleton
    @Provides
    fun provideInsertSearchHistory(historyRepository: HistoryRepository): InsertSearchHistory =
        InsertSearchHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideUpdateMyAnime(myAnimeRepository: MyAnimeRepository): UpdateMyAnime =
        UpdateMyAnimeImpl(myAnimeRepository)

    @Singleton
    @Provides
    fun provideGetAnimeDetail(detailRepository: DetailRepository): GetAnimeDetail =
        GetAnimeDetailImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetCharacterStaff(detailRepository: DetailRepository): GetCharacterStaff =
        GetCharacterStaffImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetSearchAnime(animeRepository: AnimeRepository): GetSearchAnime =
        GetSearchAnimeImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetGenreAnime(animeRepository: AnimeRepository): GetGenreAnime =
        GetGenreAnimeImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetTopAnime(animeRepository: AnimeRepository): GetTopAnime =
        GetTopAnimeImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetCurrentSeasonAnime(animeRepository: AnimeRepository): GetCurrentSeasonAnime =
        GetCurrentSeasonAnimeImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSeasonAnime(animeRepository: AnimeRepository): GetSeasonAnime =
        GetSeasonAnimeImpl(animeRepository)


}