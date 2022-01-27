package com.lexwilliam.data.model.local

data class MyAnimeRepo(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatusRepo?,
    val timeAdded: Long = System.currentTimeMillis()
)