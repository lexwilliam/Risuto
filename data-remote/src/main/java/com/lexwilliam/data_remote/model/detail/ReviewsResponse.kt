package com.lexwilliam.data_remote.model.detail


data class ReviewsResponse(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val reviews: List<ReviewResponse>
)

data class ReviewerResponse(
    val episodes_seen: Int,
    val image_url: String,
    val reviewScore: ReviewScoreResponse,
    val url: String,
    val username: String
)

data class ReviewScoreResponse(
    val animation: Int,
    val character: Int,
    val enjoyment: Int,
    val overall: Int,
    val sound: Int,
    val story: Int
)

data class ReviewResponse(
    val content: String,
    val date: String,
    val helpful_count: Int,
    val mal_id: Int,
    val reviewer: ReviewerResponse,
    val type: Any,
    val url: String
)