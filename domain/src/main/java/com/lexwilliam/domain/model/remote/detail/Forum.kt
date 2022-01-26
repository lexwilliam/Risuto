package com.lexwilliam.domain.model.remote.detail


data class Forum(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val topics: List<Topic>
)

data class Topic(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val last_post: LastPost,
    val replies: Int,
    val title: String,
    val topic_id: Int,
    val url: String
)

data class LastPost(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val url: String
)
