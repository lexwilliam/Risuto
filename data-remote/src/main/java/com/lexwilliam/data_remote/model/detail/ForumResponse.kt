package com.lexwilliam.data_remote.model.detail


data class ForumResponse(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val topics: List<TopicResponse>
)

data class TopicResponse(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val last_post: LastPostResponse,
    val replies: Int,
    val title: String,
    val topic_id: Int,
    val url: String
)

data class LastPostResponse(
    val author_name: String,
    val author_url: String,
    val date_posted: String,
    val url: String
)
