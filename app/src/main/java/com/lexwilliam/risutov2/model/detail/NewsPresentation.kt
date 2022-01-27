package com.lexwilliam.risutov2.model.detail

data class NewsPresentation(
    val articles: List<ArticlePresentation>
)

data class ArticlePresentation(
    val author_name: String,
    val author_url: String,
    val comments: Int,
    val date: String,
    val forum_url: String,
    val image_url: String,
    val intro: String,
    val title: String,
    val url: String
)