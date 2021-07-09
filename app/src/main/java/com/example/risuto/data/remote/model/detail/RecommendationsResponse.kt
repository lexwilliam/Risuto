package com.example.risuto.data.remote.model.detail

data class RecommendationsResponse(
    val recommendations: List<Recommendation>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class Recommendation(
    val image_url: String,
    val mal_id: Int,
    val recommendation_count: Int,
    val recommendation_url: String,
    val title: String,
    val url: String
)