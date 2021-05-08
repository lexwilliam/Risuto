package com.example.risuto.data.local.mapper

import com.example.risuto.data.local.model.AnimeHistoryEntity
import com.example.risuto.data.local.model.SearchHistoryEntity
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.SearchHistory

internal fun SearchHistoryEntity.toDomain(): SearchHistory {
    return SearchHistory(query)
}

internal fun AnimeHistoryEntity.toDomain(): AnimeHistory {
    return AnimeHistory(mal_id, title, image_url)
}