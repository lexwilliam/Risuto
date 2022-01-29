package com.lexwilliam.risutov2.model.local

import com.lexwilliam.domain.model.local.WatchStatus

data class MyAnimePresentation(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatusPresentation? = WatchStatusPresentation.Default,
    val timeAdded: Long = System.currentTimeMillis()
)