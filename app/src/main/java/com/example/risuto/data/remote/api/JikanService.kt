package com.chun2maru.risutomvvm.data.remote

import com.example.risuto.data.remote.model.detail.AnimeResponse
import com.example.risuto.data.remote.model.detail.CharacterStaffResponse
import com.example.risuto.data.remote.model.list.request.RequestSearch
import com.example.risuto.data.remote.model.list.request.RequestSeason
import com.example.risuto.data.remote.model.list.request.RequestTop
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
        @Query("sort") sort: String?
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

    @GET("anime/{id}")
    suspend fun getAnimeResult(
        @Path("id") id: Int
    ): AnimeResponse

    @GET("anime/{id}/characters_staff")
    suspend fun getCharacterStaffResult(
        @Path("id") id: Int
    ): CharacterStaffResponse

}