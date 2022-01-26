package com.lexwilliam.data.model.remote.detail

data class RecommendationsRepo(
    val recommendations: List<RecommendationRepo>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class RecommendationRepo(
    val image_url: String,
    val mal_id: Int,
    val recommendation_count: Int,
    val recommendation_url: String,
    val title: String,
    val url: String
)
