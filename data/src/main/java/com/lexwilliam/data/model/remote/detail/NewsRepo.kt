package com.lexwilliam.data.model.remote.detail


data class NewsRepo(
    val articles: List<ArticleRepo>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class ArticleRepo(
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
