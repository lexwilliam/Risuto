package com.lexwilliam.data_remote

import com.lexwilliam.data_remote.model.detail.*
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanService {

    @GET("anime/{id}")
    suspend fun getAnimeResult(
        @Path("id") id: Int
    ): AnimeDetailResponse

    @GET("anime/{id}/characters_staff")
    suspend fun getCharacterStaffResult(
        @Path("id") id: Int
    ): CharacterStaffResponse

    @GET("anime/{id}/episodes")
    suspend fun getEpisodesResult(
        @Path("id") id: Int
    ): EpisodesResponse

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