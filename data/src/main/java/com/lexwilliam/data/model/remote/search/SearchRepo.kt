package com.lexwilliam.data.model.remote.search

data class SearchRepo(
    val request_hash : String,
    val request_cached : Boolean,
    val request_cache_expiry : Int,
    val results : List<SearchAnimeRepo>,
    val last_page : Int
)