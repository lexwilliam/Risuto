package com.lexwilliam.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchHistory")
data class SearchHistoryEntity(
    @PrimaryKey
    val query: String,
    val timeAdded: Long = System.currentTimeMillis()
)