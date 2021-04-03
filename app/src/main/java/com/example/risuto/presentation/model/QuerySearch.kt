package com.example.risuto.presentation.model

data class QuerySearch(
    val q: String?,
    val type: String?,
    val status: String?,
    val genre: Int?,
    val limit: Int?
)