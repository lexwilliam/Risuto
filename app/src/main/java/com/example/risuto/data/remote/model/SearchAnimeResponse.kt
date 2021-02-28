package com.chun2maru.risutomvvm.data.remote.model

import com.squareup.moshi.Json

data class SearchAnimeResponse (
        @field:Json(name = "mal_id") val mal_id : Int?,
        @field:Json(name = "url") val url : String?,
        @field:Json(name = "image_url") val image_url : String?,
        @field:Json(name = "title") val title : String?,
        @field:Json(name = "airing") val airing : Boolean?,
        @field:Json(name = "synopsis") val synopsis : String?,
        @field:Json(name = "type") val type : String?,
        @field:Json(name = "episodes") val episodes : Int?,
        @field:Json(name = "score") val score : Float?,
        @field:Json(name = "start_date") val start_date : String?,
        @field:Json(name = "end_date") val end_date : String?,
        @field:Json(name = "members") val members : Int?,
        @field:Json(name = "rated") val rated : String?

)
