package com.lexwilliam.data_local

import androidx.room.TypeConverter
import com.lexwilliam.data_local.model.WatchStatusEntity

class Converters {
    @TypeConverter
    fun fromWatchStatus(value: String?): WatchStatusEntity? {
        return value?.let { enumValueOf<WatchStatusEntity>(it) }
    }

    @TypeConverter
    fun watchStatusToString(status: WatchStatusEntity?): String? {
        return status?.name
    }
}