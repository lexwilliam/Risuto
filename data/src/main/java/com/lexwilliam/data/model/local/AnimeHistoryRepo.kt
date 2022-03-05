package com.lexwilliam.data.model.local

data class AnimeHistoryRepo(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val timeAdded: Long = System.currentTimeMillis()
)