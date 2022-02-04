package com.lexwilliam.risuto.model.detail

data class StatsPresentation(
    val completed: Int,
    val dropped: Int,
    val on_hold: Int,
    val plan_to_watch: Int,
    val scores: ReviewScorePresentation,
    val total: Int,
    val watching: Int
)