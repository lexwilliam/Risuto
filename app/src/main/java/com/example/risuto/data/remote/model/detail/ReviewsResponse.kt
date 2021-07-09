package com.example.risuto.data.remote.model.detail

data class ReviewsResponse(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val reviews: List<Review>
)

data class Review(
    val content: String,
    val date: String,
    val helpful_count: Int,
    val mal_id: Int,
    val reviewer: Reviewer,
    val type: Any,
    val url: String
)

data class Reviewer(
    val episodes_seen: Int,
    val image_url: String,
    val reviewScore: ReviewScore,
    val url: String,
    val username: String
)

data class ReviewScore(
    val animation: Int,
    val character: Int,
    val enjoyment: Int,
    val overall: Int,
    val sound: Int,
    val story: Int
)