package com.lexwilliam.data_remote.model.detail

data class RecommendationsResponse(
    val recommendations: List<RecommendationResponse>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class RecommendationResponse(
    val image_url: String,
    val mal_id: Int,
    val recommendation_count: Int,
    val recommendation_url: String,
    val title: String,
    val url: String
)
