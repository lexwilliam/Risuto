package com.chun2maru.risutomvvm.data.remote

import com.example.risuto.data.remote.model.detail.*
import com.example.risuto.data.remote.model.list.request.RequestSearch
import com.example.risuto.data.remote.model.list.request.RequestSeason
import com.example.risuto.data.remote.model.list.request.RequestTop
import com.example.risuto.domain.model.detail.Forum
import com.example.risuto.domain.model.detail.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanService {

    @GET("search/anime")
    suspend fun getSearchAnimeResult(
        @Query("q") q: String?,
        @Query("type") type: String?,
        @Query("status") status: String?,
        @Query("genre") genre: Int?,
        @Query("limit") limit: Int?,
        @Query("order_by") orderBy: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int?
    ): RequestSearch

    @GET("top/anime/{page}/{subType}")
    suspend fun getTopResult(
        @Path("page") page: Int,
        @Path("subType") subType: String
    ): RequestTop

    @GET("season/{year}/{season}")
    suspend fun getSeasonAnimeResult(
        @Path("year") year: Int,
        @Path("season") season: String
    ): RequestSeason

    @GET("season/archive")
    suspend fun getSeasonArchiveResult(): SeasonArchiveResponse

    @GET("anime/{id}")
    suspend fun getAnimeResult(
        @Path("id") id: Int
    ): AnimeResponse

    @GET("anime/{id}/characters_staff")
    suspend fun getCharacterStaffResult(
        @Path("id") id: Int
    ): CharacterStaffResponse

    @GET("anime/{id}/episodes")
    suspend fun getEpisodesResult(
        @Path("id") id: Int
    ): EpisodeResponse

    @GET("anime/{id}/forum")
    suspend fun getForumResult(
        @Path("id") id: Int
    ): ForumResponse

    @GET("anime/{id}/moreinfo")
    suspend fun getMoreInfoResult(
        @Path("id") id: Int
    ): MoreInfoResponse

    @GET("anime/{id}/news")
    suspend fun getNewsResult(
        @Path("id") id: Int
    ): NewsResponse

    @GET("anime/{id}/pictures")
    suspend fun getPicturesResult(
        @Path("id") id: Int
    ): PicturesResponse

    @GET("anime/{id}/recommendations")
    suspend fun getRecommendationsResult(
        @Path("id") id: Int
    ): RecommendationsResponse

    @GET("anime/{id}/reviews")
    suspend fun getReviewsResult(
        @Path("id") id: Int
    ): ReviewsResponse

    @GET("anime/{id}/stats")
    suspend fun getStatsResult(
        @Path("id") id: Int
    ): StatsResponse

    @GET("anime/{id}/videos")
    suspend fun getVideosResult(
        @Path("id") id: Int
    ): VideosResponse

}