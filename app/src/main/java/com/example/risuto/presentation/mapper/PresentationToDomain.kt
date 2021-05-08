package com.example.risuto.presentation.mapper

import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.SearchHistory
import com.example.risuto.presentation.model.AnimeHistoryPresentation
import com.example.risuto.presentation.model.SearchHistoryPresentation

internal fun SearchHistoryPresentation.toDomain(): SearchHistory {
    return SearchHistory(query = query)
}

internal fun AnimeHistoryPresentation.toDomain(): AnimeHistory {
    return AnimeHistory(mal_id, title, image_url)
}