package com.lexwilliam.risuto.model.detail

data class PicturesPresentation(
    val pictures: List<PicturePresentation>
)

data class PicturePresentation(
    val large: String,
    val small: String
)