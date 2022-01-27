package com.lexwilliam.risutov2.model.detail

data class RecommendationsPresentation(
    val recommendations: List<RecommendationPresentation>,
)

data class RecommendationPresentation(
    val image_url: String,
    val mal_id: Int,
    val recommendation_count: Int,
    val recommendation_url: String,
    val title: String,
    val url: String
)
