package com.example.risuto.presentation.model

import com.example.risuto.data.remote.model.detail.Character
import com.example.risuto.data.remote.model.detail.Staff

data class CharacterStaffPresentation(
    val characters: List<Character>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val staff: List<Staff>
) {
    constructor() : this(
        listOf(), 0, false, "", listOf()
    )
}