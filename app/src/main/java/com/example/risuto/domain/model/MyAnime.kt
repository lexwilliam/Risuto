package com.example.risuto.domain.model

import com.example.risuto.data.local.model.WatchStatus

data class MyAnime(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatus,
    val timeAdded: Long
)