package com.example.risuto.presentation.model

import com.example.risuto.data.local.model.WatchStatus

data class MyAnimePresentation(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatus
)