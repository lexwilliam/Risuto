package com.lexwilliam.domain.di

import com.lexwilliam.domain.repository.*
import com.lexwilliam.domain.usecase.local.*
import com.lexwilliam.domain.usecase.remote.*
import com.lexwilliam.domain.usecase.remote.anime.*
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
    fun provideGetEpisodes(detailRepository: DetailRepository): GetEpisodes =
        GetEpisodesImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetForum(detailRepository: DetailRepository): GetForum =
        GetForumImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetMoreInfo(detailRepository: DetailRepository): GetMoreInfo =
        GetMoreInfoImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetNews(detailRepository: DetailRepository): GetNews =
        GetNewsImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetPictures(detailRepository: DetailRepository): GetPictures =
        GetPicturesImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetRecommendations(detailRepository: DetailRepository): GetRecommendations =
        GetRecommendationsImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetReviews(detailRepository: DetailRepository): GetReviews =
        GetReviewsImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetStats(detailRepository: DetailRepository): GetStats =
        GetStatsImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetVideos(detailRepository: DetailRepository): GetVideos =
        GetVideosImpl(detailRepository)

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
    fun provideGetUserInfo(userRepository: UserRepository): GetUserInfo =
        GetUserInfoImpl(userRepository)

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
    fun provideGetMyAnimeStatus(detailRepository: DetailRepository): GetMyAnimeStatus =
        GetMyAnimeStatusImpl(detailRepository)

    @Singleton
    @Provides
    fun provideGetTopAnimeV4(animeRepository: AnimeRepository): GetTopAnimeV4 =
        GetTopAnimeV4Impl(animeRepository)

    @Singleton
    @Provides
    fun provideGetAnimeById(animeRepository: AnimeRepository): GetAnimeById =
        GetAnimeByIdImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetRecentAnimeRecommendation(animeRepository: AnimeRepository): GetSchedules =
        GetSchedulesImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSearchAnimeV4(animeRepository: AnimeRepository): GetSearchAnimeV4 =
        GetSearchAnimeV4Impl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSeason(animeRepository: AnimeRepository): GetSeason =
        GetSeasonImpl(animeRepository)

    @Singleton
    @Provides
    fun provideGetSeasonNow(animeRepository: AnimeRepository): GetSeasonNow =
        GetSeasonNowImpl(animeRepository)
}