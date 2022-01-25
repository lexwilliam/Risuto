package com.lexwilliam.data.model.detail


data class ReviewsRepo(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val reviews: List<ReviewRepo>
)

data class ReviewerRepo(
    val episodes_seen: Int,
    val image_url: String,
    val reviewScore: ReviewScoreRepo,
    val url: String,
    val username: String
)

data class ReviewScoreRepo(
    val animation: Int,
    val character: Int,
    val enjoyment: Int,
    val overall: Int,
    val sound: Int,
    val story: Int
)

data class ReviewRepo(
    val content: String,
    val date: String,
    val helpful_count: Int,
    val mal_id: Int,
    val reviewer: ReviewerRepo,
    val type: Any,
    val url: String
)