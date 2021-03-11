package com.example.risuto.presentation.model

data class RowStylePresentation(
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val synopsis : String,
    val type : String,
    val episodes : Int,
    val score : Float,
    val members : Int
)