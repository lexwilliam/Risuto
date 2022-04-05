package com.lexwilliam.data_remote.model.anime

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class SeasonListResponse(
    val data: List<Data>
) {
    data class Data(
        val year: Int,
        val seasons: List<String>
    )
}