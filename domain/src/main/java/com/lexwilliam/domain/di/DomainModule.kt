package com.lexwilliam.domain.di

import com.lexwilliam.domain.repository.AnimeRepository
import com.lexwilliam.domain.repository.CharacterRepository
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.repository.HistoryRepository
import com.lexwilliam.domain.repository.OAuthRepository
import com.lexwilliam.domain.repository.PersonRepository
import com.lexwilliam.domain.repository.UserRepository
import com.lexwilliam.domain.usecase.ContinueAsGuest
import com.lexwilliam.domain.usecase.ContinueAsGuestImpl
import com.lexwilliam.domain.usecase.DeleteAllAnimeHistory
import com.lexwilliam.domain.usecase.DeleteAllAnimeHistoryImpl
import com.lexwilliam.domain.usecase.DeleteAllSearchHistory
import com.lexwilliam.domain.usecase.DeleteAllSearchHistoryImpl
import com.lexwilliam.domain.usecase.DeleteAnimeByTitle
import com.lexwilliam.domain.usecase.DeleteAnimeByTitleImpl
import com.lexwilliam.domain.usecase.DeleteSearchHistory
import com.lexwilliam.domain.usecase.DeleteSearchHistoryImpl
import com.lexwilliam.domain.usecase.DeleteUserAnimeStatus
import com.lexwilliam.domain.usecase.DeleteUserAnimeStatusImpl
import com.lexwilliam.domain.usecase.GetAccessTokenFromCache
import com.lexwilliam.domain.usecase.GetAccessTokenFromCacheImpl
import com.lexwilliam.domain.usecase.GetAccessTokenImpl
import com.lexwilliam.domain.usecase.GetAnimeCharacters
import com.lexwilliam.domain.usecase.GetAnimeCharactersImpl
import com.lexwilliam.domain.usecase.GetAnimeDetails
import com.lexwilliam.domain.usecase.GetAnimeDetailsImpl
import com.lexwilliam.domain.usecase.GetAnimeHistory
import com.lexwilliam.domain.usecase.GetAnimeHistoryImpl
import com.lexwilliam.domain.usecase.GetAnimeStaff
import com.lexwilliam.domain.usecase.GetAnimeStaffImpl
import com.lexwilliam.domain.usecase.GetAnimeVideos
import com.lexwilliam.domain.usecase.GetAnimeVideosImpl
import com.lexwilliam.domain.usecase.GetAuthState
import com.lexwilliam.domain.usecase.GetAuthStateImpl
import com.lexwilliam.domain.usecase.GetAuthTokenLink
import com.lexwilliam.domain.usecase.GetAuthTokenLinkImpl
import com.lexwilliam.domain.usecase.GetCharacterById
import com.lexwilliam.domain.usecase.GetCharacterByIdImpl
import com.lexwilliam.domain.usecase.GetCodeChallenge
import com.lexwilliam.domain.usecase.GetCodeChallengeImpl
import com.lexwilliam.domain.usecase.GetExpiresInFromCache
import com.lexwilliam.domain.usecase.GetExpiresInFromCacheImpl
import com.lexwilliam.domain.usecase.GetPeopleById
import com.lexwilliam.domain.usecase.GetPeopleByIdImpl
import com.lexwilliam.domain.usecase.GetRefreshTokenFromCache
import com.lexwilliam.domain.usecase.GetRefreshTokenFromCacheImpl
import com.lexwilliam.domain.usecase.GetSchedules
import com.lexwilliam.domain.usecase.GetSchedulesImpl
import com.lexwilliam.domain.usecase.GetSearchAnime
import com.lexwilliam.domain.usecase.GetSearchAnimeImpl
import com.lexwilliam.domain.usecase.GetSearchAnimePaging
import com.lexwilliam.domain.usecase.GetSearchAnimePagingImpl
import com.lexwilliam.domain.usecase.GetSearchHistory
import com.lexwilliam.domain.usecase.GetSearchHistoryImpl
import com.lexwilliam.domain.usecase.GetSeason
import com.lexwilliam.domain.usecase.GetSeasonImpl
import com.lexwilliam.domain.usecase.GetSeasonList
import com.lexwilliam.domain.usecase.GetSeasonListImpl
import com.lexwilliam.domain.usecase.GetSeasonNow
import com.lexwilliam.domain.usecase.GetSeasonNowImpl
import com.lexwilliam.domain.usecase.GetTopAnime
import com.lexwilliam.domain.usecase.GetTopAnimeImpl
import com.lexwilliam.domain.usecase.GetUserAnimeList
import com.lexwilliam.domain.usecase.GetUserAnimeListImpl
import com.lexwilliam.domain.usecase.GetUserProfile
import com.lexwilliam.domain.usecase.GetUserProfileImpl
import com.lexwilliam.domain.usecase.InsertAnimeHistory
import com.lexwilliam.domain.usecase.InsertAnimeHistoryImpl
import com.lexwilliam.domain.usecase.InsertSearchHistory
import com.lexwilliam.domain.usecase.InsertSearchHistoryImpl
import com.lexwilliam.domain.usecase.Logout
import com.lexwilliam.domain.usecase.LogoutImpl
import com.lexwilliam.domain.usecase.SetAccessToken
import com.lexwilliam.domain.usecase.SetCodeChallenge
import com.lexwilliam.domain.usecase.SetCodeChallengeImpl
import com.lexwilliam.domain.usecase.SetRefreshToken
import com.lexwilliam.domain.usecase.SetRefreshTokenImpl
import com.lexwilliam.domain.usecase.UpdateUserAnimeStatus
import com.lexwilliam.domain.usecase.UpdateUserAnimeStatusImpl
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

    @Singleton
    @Provides
    fun provideGetCharacterById(characterRepository: CharacterRepository): GetCharacterById =
        GetCharacterByIdImpl(characterRepository)

    @Singleton
    @Provides
    fun provideContinueAsGuest(userRepository: UserRepository): ContinueAsGuest =
        ContinueAsGuestImpl(userRepository)

    @Singleton
    @Provides
    fun provideLogout(oAuthRepository: OAuthRepository): Logout =
        LogoutImpl(oAuthRepository)
}