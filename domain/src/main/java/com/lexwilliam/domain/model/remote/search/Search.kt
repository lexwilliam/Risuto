package com.lexwilliam.domain.model.remote.search

data class Search(
    val request_hash : String,
    val request_cached : Boolean,
    val request_cache_expiry : Int,
    val results : List<SearchAnime>,
    val last_page : Int
)