package com.example.risuto.data.remote.model.list.request

import com.example.risuto.data.remote.model.list.SearchAnimeResponse
import com.squareup.moshi.Json

data class RequestSearch(
    @field:Json(name = "request_hash") val request_hash : String,
    @field:Json(name ="request_cached") val request_cached : Boolean,
    @field:Json(name ="request_cache_expiry") val request_cache_expiry : Int,
    @field:Json(name ="results") val results : List<SearchAnimeResponse>,
    @field:Json(name ="last_page") val last_page : Int
)