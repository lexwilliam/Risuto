package com.example.risuto.data.local.mapper

import com.example.risuto.data.local.model.AnimeHistoryEntity
import com.example.risuto.data.local.model.SearchHistoryEntity
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.SearchHistory

internal fun SearchHistory.toEntity(): SearchHistoryEntity {
    return SearchHistoryEntity(query = query)
}

internal fun AnimeHistory.toEntity(): AnimeHistoryEntity {
    return AnimeHistoryEntity(
        mal_id = mal_id,
        title = title,
        image_url = image_url
    )
}