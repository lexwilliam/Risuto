package com.lexwilliam.domain.model.remote.anime

data class SeasonList(
    val data: List<Data>
) {
    data class Data(
        val year: Int,
        val seasons: List<String>
    )
}