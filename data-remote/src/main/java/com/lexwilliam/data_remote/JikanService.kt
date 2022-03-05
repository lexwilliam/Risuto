package com.lexwilliam.data_remote

import com.lexwilliam.data_remote.model.anime.AnimeResponse
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

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): AnimeResponse.Data

//    @GET("anime/{id}/characters")
//    suspend fun getAnimeCharacters(
//        @Path("id") id:
//    ):
//
//    @GET("genres/anime")
//    suspend fun getAnimesGenres(): AnimeResponse.Data.Genre

}