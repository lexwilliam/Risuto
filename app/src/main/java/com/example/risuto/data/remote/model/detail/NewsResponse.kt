package com.example.risuto.data.remote.model.detail

data class NewsResponse(
    val articles: List<Article>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class Article(
    val author_name: String,
    val author_url: String,
    val comments: Int,
    val date: String,
    val forum_url: String,
    val image_url: String,
    val intro: String,
    val title: String,
    val url: String
)