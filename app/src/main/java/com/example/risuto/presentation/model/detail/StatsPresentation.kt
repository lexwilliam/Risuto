package com.example.risuto.presentation.model.detail

import com.example.risuto.data.remote.model.detail.ReviewScore

data class StatsPresentation(
    val completed: Int,
    val dropped: Int,
    val on_hold: Int,
    val plan_to_watch: Int,
    val scores: ReviewScore,
    val total: Int,
    val watching: Int
)