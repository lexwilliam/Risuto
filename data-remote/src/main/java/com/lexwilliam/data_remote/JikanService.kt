package com.lexwilliam.data_remote

import com.lexwilliam.data_remote.model.anime.AnimeCharactersResponse
import com.lexwilliam.data_remote.model.anime.AnimeResponse
import com.lexwilliam.data_remote.model.anime.AnimeStaffResponse
import com.lexwilliam.data_remote.model.anime.AnimeVideosResponse
import com.lexwilliam.data_remote.model.anime.SeasonListResponse
import com.lexwilliam.data_remote.model.character.CharacterDetailResponse
import com.lexwilliam.data_remote.model.people.PersonResponse
import com.lexwilliam.data_remote.model.user.UserProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanService {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("anime")
    suspend fun getSearchAnime(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("q") q: String?,
        @Query("type") type: String?,
        @Query("score") score: Double?,
        @Query("min_score") minScore: Double?,
        @Query("max_score") maxScore: Double?,
        @Query("status") status: String?,
        @Query("rating") rating: String?,
        @Query("sfw") sfw: Boolean?,
        @Query("genres") genres: String?,
        @Query("genres_exclude") genresExclude: String?,
        @Query("order_by") orderBy: String?,
        @Query("sort") sort: String?,
        @Query("letter") letter: String?,
        @Query("producer") producer: String?
    ): AnimeResponse

    @GET("schedules")
    suspend fun getSchedules(
        @Query("filter") dayOfWeek: String
    ): AnimeResponse

    @GET("seasons/{year}/{season}")
    suspend fun getSeason(
        @Path("year") year: Int,
        @Path("season") season: String
    ): AnimeResponse

    @GET("seasons/now")
    suspend fun getSeasonNow(): AnimeResponse

    @GET("seasons")
    suspend fun getSeasonList(): SeasonListResponse

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(
        @Path("id") id: Int
    ): AnimeCharactersResponse

    @GET("anime/{id}/videos")
    suspend fun getAnimeVideos(
        @Path("id") id: Int
    ): AnimeVideosResponse

    @GET("anime/{id}/staff")
    suspend fun getAnimeStaff(
        @Path("id") id: Int
    ): AnimeStaffResponse

    @GET("users/{username}/full")
    suspend fun getUserProfile(
        @Path("username") username: String
    ): UserProfileResponse

    @GET("people/{id}/full")
    suspend fun getPersonById(
        @Path("id") id: Int
    ): PersonResponse

    @GET("characters/{id}/full")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDetailResponse

}