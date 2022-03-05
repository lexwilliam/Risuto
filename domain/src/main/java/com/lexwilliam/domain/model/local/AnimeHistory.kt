package com.lexwilliam.domain.model.local

data class AnimeHistory(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val timeAdded: Long = System.currentTimeMillis()
)