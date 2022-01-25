package com.lexwilliam.data_local

import androidx.room.TypeConverter
import com.lexwilliam.data_local.model.WatchStatus

class Converters {
    @TypeConverter
    fun fromWatchStatus(value: String?): WatchStatus? {
        return value?.let { enumValueOf<WatchStatus>(it) }
    }

    @TypeConverter
    fun watchStatusToString(status: WatchStatus?): String? {
        return status?.name
    }
}