package com.lexwilliam.data.model.detail


data class ForumRepo(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val topics: List<TopicRepo>
)

data class TopicRepo(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val last_post: LastPostRepo,
    val replies: Int,
    val title: String,
    val topic_id: Int,
    val url: String
)

data class LastPostRepo(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val url: String
)
