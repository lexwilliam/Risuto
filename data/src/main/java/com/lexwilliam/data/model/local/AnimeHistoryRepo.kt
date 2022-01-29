package com.lexwilliam.data.model.local

data class AnimeHistoryRepo(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val synopsis : String,
    val type : String,
    val episodes : Int,
    val score : Double,
    val members : Int,
    val timeAdded: Long = System.currentTimeMillis()
)