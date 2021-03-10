package com.example.risuto.presentation.model.custom

data class GridStylePresentation(
    val mal_id: Int,
    val image_url: String,
    val title: String,
    val type: String,
    val episodes: Int,
    val score: Float,
    val members: Int
)