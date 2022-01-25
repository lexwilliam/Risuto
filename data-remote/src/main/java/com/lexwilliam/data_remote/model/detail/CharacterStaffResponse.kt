package com.lexwilliam.data_remote.model.detail

data class CharacterStaffResponse(
    val characters: List<CharacterResponse>?,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val staff: List<StaffResponse>?
)

data class CharacterResponse(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val role: String,
    val url: String,
    val voice_actors: List<VoiceActorResponse>
)

data class StaffResponse(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val positions: List<String>,
    val url: String
)

data class VoiceActorResponse(
    val image_url: String,
    val language: String,
    val mal_id: Int,
    val name: String,
    val url: String
)