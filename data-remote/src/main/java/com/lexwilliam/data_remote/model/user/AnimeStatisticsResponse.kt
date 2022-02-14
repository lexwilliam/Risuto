package com.lexwilliam.data_remote.model.user

data class AnimeStatisticsResponse(
    val mean_score: Double,
    val num_days: Double,
    val num_days_completed: Double,
    val num_days_dropped: Int,
    val num_days_on_hold: Int,
    val num_days_watched: Double,
    val num_days_watching: Double,
    val num_episodes: Int,
    val num_items: Int,
    val num_items_completed: Int,
    val num_items_dropped: Int,
    val num_items_on_hold: Int,
    val num_items_plan_to_watch: Int,
    val num_items_watching: Int,
    val num_times_rewatched: Int
)