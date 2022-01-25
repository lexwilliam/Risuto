package com.lexwilliam.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myAnime")
data class MyAnimeEntity(
    @PrimaryKey val mal_id : Int,
    val image_url : String,
    val title : String,
    val myScore: Int,
    val watchStatus: WatchStatus?,
    val timeAdded: Long = System.currentTimeMillis()
)

enum class WatchStatus{
    Completed, Watching, PlanToWatch, Dropped, OnHold
}