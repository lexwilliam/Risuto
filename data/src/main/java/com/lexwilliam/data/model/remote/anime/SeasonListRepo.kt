package com.lexwilliam.data.model.remote.anime

data class SeasonListRepo(
    val data: List<Data>
) {
    data class Data(
        val year: Int,
        val seasons: List<String>
    )
}