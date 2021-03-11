package com.chun2maru.risutomvvm.data.remote

import com.chun2maru.risutomvvm.data.remote.model.RequestSearch
import com.example.risuto.data.remote.model.AnimeResponse
import com.example.risuto.data.remote.model.RequestTop
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanService {

    @GET("search/anime")
    suspend fun getSearchAnimeResult(
        @Query("q") name: String
    ): RequestSearch

    @GET("top/anime/{page}/{subType}")
    suspend fun getTopResult(
        @Path("page") page: Int,
        @Path("subType") subType: String
    ): RequestTop

    @GET("anime/{id}")
    suspend fun getAnimeResult(
        @Path("id") id: Int
    ): AnimeResponse

}