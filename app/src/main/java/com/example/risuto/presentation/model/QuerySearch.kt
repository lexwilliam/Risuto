package com.example.risuto.presentation.model

data class QuerySearch(
    var q: String? = null,
    val type: String? = null,
    val status: String? = null,
    val genre: Int? = null,
    val limit: Int? = null
)