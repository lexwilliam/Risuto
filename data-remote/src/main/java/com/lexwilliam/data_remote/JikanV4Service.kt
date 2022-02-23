package com.lexwilliam.data_remote

import com.lexwilliam.data_remote.model.anime.AnimeResponse
import retrofit2.http.GET

interface JikanV4Service {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

}