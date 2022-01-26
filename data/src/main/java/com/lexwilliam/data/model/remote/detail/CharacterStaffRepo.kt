package com.lexwilliam.data.model.remote.detail

data class CharacterStaffRepo(
    val characters: List<CharacterRepo>?,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val staff: List<StaffRepo>?
)

data class CharacterRepo(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val role: String,
    val url: String,
    val voice_actors: List<VoiceActorRepo>
)

data class StaffRepo(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val positions: List<String>,
    val url: String
)

data class VoiceActorRepo(
    val image_url: String,
    val language: String,
    val mal_id: Int,
    val name: String,
    val url: String
)