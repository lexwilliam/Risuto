package com.lexwilliam.risuto.model.local

data class MyAnimePresentation(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatusPresentation? = WatchStatusPresentation.Default,
    val timeAdded: Long = System.currentTimeMillis()
)