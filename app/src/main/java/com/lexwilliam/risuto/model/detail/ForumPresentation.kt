package com.lexwilliam.risuto.model.detail

data class ForumPresentation(
    val topics: List<TopicPresentation>
)

data class TopicPresentation(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val last_post: LastPostPresentation,
    val replies: Int,
    val title: String,
    val topic_id: Int,
    val url: String
)

data class LastPostPresentation(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val url: String
)
