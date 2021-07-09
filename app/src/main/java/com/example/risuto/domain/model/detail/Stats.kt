package com.example.risuto.domain.model.detail

import com.example.risuto.data.remote.model.detail.ReviewScore

data class Stats(
    val completed: Int,
    val dropped: Int,
    val on_hold: Int,
    val plan_to_watch: Int,
    val scores: ReviewScore,
    val total: Int,
    val watching: Int
)