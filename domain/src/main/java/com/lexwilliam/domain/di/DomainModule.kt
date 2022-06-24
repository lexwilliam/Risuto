package com.lexwilliam.domain.di

import com.lexwilliam.domain.repository.*
import com.lexwilliam.domain.usecase.DeleteAllAnimeHistory
import com.lexwilliam.domain.usecase.DeleteAllAnimeHistoryImpl
import com.lexwilliam.domain.usecase.*
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
    fun provideDeleteSearchHistory(historyRepository: HistoryRepository): DeleteSearchHistory =
        DeleteSearchHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideGetAnimeHistory(historyRepository: HistoryRepository): GetAnimeHistory =
        GetAnimeHistoryImpl(historyRepository)

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
    fun provideInsertSearchHistory(historyRepository: HistoryRepository): InsertSearchHistory =
        InsertSearchHistoryImpl(historyRepository)

    @Singleton
    @Provides
    fun provideGetSearchAnime(animeRepository: AnimeRepository): GetSearchAnime =
        GetSearchAnimeImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetAccessToken(oAuthRepository: OAuthRepository): SetAccessToken =
        GetAccessTokenImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetAuthTokenLink(oAuthRepository: OAuthRepository): GetAuthTokenLink =
        GetAuthTokenLinkImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetAuthState(oAuthRepository: OAuthRepository): GetAuthState =
        GetAuthStateImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetCodeChallenge(oAuthRepository: OAuthRepository): GetCodeChallenge =
        GetCodeChallengeImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideSetCodeChallengeAndState(oAuthRepository: OAuthRepository): SetCodeChallenge =
        SetCodeChallengeImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideRefreshToken(oAuthRepository: OAuthRepository): SetRefreshToken =
        SetRefreshTokenImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetAccessTokenFromCache(oAuthRepository: OAuthRepository): GetAccessTokenFromCache =
        GetAccessTokenFromCacheImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetRefreshTokenFromCache(oAuthRepository: OAuthRepository): GetRefreshTokenFromCache =
        GetRefreshTokenFromCacheImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetExpiresInFromCache(oAuthRepository: OAuthRepository): GetExpiresInFromCache =
        GetExpiresInFromCacheImpl(oAuthRepository)

    @Singleton
    @Provides
    fun provideGetUserAnimeList(userRepository: UserRepository): GetUserAnimeList =
        GetUserAnimeListImpl(userRepository)


    @Singleton
    @Provides
    fun provideGetTopAnimeV4(animeRepository: AnimeRepository): GetTopAnime =
        GetTopAnimeImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetRecentAnimeRecommendation(animeRepository: AnimeRepository): GetSchedules =
        GetSchedulesImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSeason(animeRepository: AnimeRepository): GetSeason =
        GetSeasonImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSeasonNow(animeRepository: AnimeRepository): GetSeasonNow =
        GetSeasonNowImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSearchAnimePaging(animeRepository: AnimeRepository): GetSearchAnimePaging =
        GetSearchAnimePagingImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetAnimeDetails(detailRepository: DetailRepository): GetAnimeDetails =
        GetAnimeDetailsImpl(detailRepository)

    @Singleton
    @Provides
    fun provideUpdateUserAnimeStatus(userRepository: UserRepository): UpdateUserAnimeStatus =
        UpdateUserAnimeStatusImpl(userRepository)

    @Singleton
    @Provides
    fun provideGetAnimeCharacters(detailRepository: DetailRepository): GetAnimeCharacters =
        GetAnimeCharactersImpl(detailRepository)

    @Singleton
    @Provides
    fun provideDeleteAnimeStatus(userRepository: UserRepository): DeleteUserAnimeStatus =
        DeleteUserAnimeStatusImpl(userRepository)

    @Singleton
    @Provides
    fun provideGetSeasonList(animeRepository: AnimeRepository): GetSeasonList =
        GetSeasonListImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetAnimeVideos(detailRepository: DetailRepository): GetAnimeVideos =
        GetAnimeVideosImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetAnimeStaff(detailRepository: DetailRepository): GetAnimeStaff =
        GetAnimeStaffImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetUserProfile(userRepository: UserRepository): GetUserProfile =
        GetUserProfileImpl(userRepository)

    @Singleton
    @Provides
    fun provideGetPeopleById(personRepository: PersonRepository): GetPeopleById =
        GetPeopleByIdImpl(personRepository)
}