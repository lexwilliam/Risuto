package com.lexwilliam.risuto.model

data class SeasonListPresentation(
    val data: List<Data>
) {
    data class Data(
        val year: Int,
        val seasons: List<String>
    )
}