package com.example.risuto.presentation.mapper

import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.model.SearchHistory
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.SearchHistoryPresentation

internal fun SearchHistoryPresentation.toDomain(): SearchHistory {
    return SearchHistory(query = query)
}

internal fun AnimePresentation.toDomain(): AnimeHistory{
    return AnimeHistory(mal_id, image_url, title, synopsis, type, episodes, score, members)
}