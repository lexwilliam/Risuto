package com.example.risuto.data.remote.model.detail

data class CharacterStaffResponse(
    val characters: List<Character>?,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val staff: List<Staff>?
)

data class Staff(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val positions: List<String>,
    val url: String
)

data class VoiceActor(
    val image_url: String,
    val language: String,
    val mal_id: Int,
    val name: String,
    val url: String
)

data class Character(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val role: String,
    val url: String,
    val voice_actors: List<VoiceActor>
)