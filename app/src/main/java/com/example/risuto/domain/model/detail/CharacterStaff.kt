package com.example.risuto.domain.model.detail

import com.example.risuto.data.remote.model.detail.Character
import com.example.risuto.data.remote.model.detail.Staff

data class CharacterStaff(
    val characters: List<Character>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val staff: List<Staff>
)