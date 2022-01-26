package com.lexwilliam.domain.model.local

data class MyAnime(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatus?,
    val timeAdded: Long = System.currentTimeMillis()
)