package com.lexwilliam.risutov2.model.detail

data class ReviewsPresentation(
    val reviews: List<ReviewPresentation>
)

data class ReviewerPresentation(
    val episodes_seen: Int,
    val image_url: String,
    val reviewScore: ReviewScorePresentation,
    val url: String,
    val username: String
)

data class ReviewScorePresentation(
    val animation: Int,
    val character: Int,
    val enjoyment: Int,
    val overall: Int,
    val sound: Int,
    val story: Int
)

data class ReviewPresentation(
    val content: String,
    val date: String,
    val helpful_count: Int,
    val mal_id: Int,
    val reviewer: ReviewerPresentation,
    val type: Any,
    val url: String
)