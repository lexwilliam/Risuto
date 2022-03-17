package com.lexwilliam.data_remote.model.anime

data class SeasonListResponse(
    val data: List<Data>
) {
    data class Data(
        val year: Int,
        val seasons: List<String>
    )
}