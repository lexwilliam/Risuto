package com.lexwilliam.risutov2.model.detail

data class CharacterStaffPresentation(
    val characters: List<CharacterPresentation>?,
    val staff: List<StaffPresentation>?
)

data class CharacterPresentation(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val role: String,
    val url: String,
    val voice_actors: List<VoiceActorPresentation>
)

data class StaffPresentation(
    val image_url: String,
    val mal_id: Int,
    val name: String,
    val positions: List<String>,
    val url: String
)

data class VoiceActorPresentation(
    val image_url: String,
    val language: String,
    val mal_id: Int,
    val name: String,
    val url: String
)