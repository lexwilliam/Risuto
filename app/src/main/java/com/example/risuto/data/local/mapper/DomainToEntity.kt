package com.example.risuto.data.local.mapper

import com.example.risuto.data.local.model.AnimeHistoryEntity
import com.example.risuto.data.local.model.MyAnimeEntity
import com.example.risuto.data.local.model.SearchHistoryEntity
import com.example.risuto.domain.model.history.AnimeHistory
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.model.history.SearchHistory

internal fun SearchHistory.toEntity(): SearchHistoryEntity {
    return SearchHistoryEntity(query = query)
}

internal fun AnimeHistory.toEntity(): AnimeHistoryEntity {
    return AnimeHistoryEntity(
        mal_id = mal_id,
        image_url = image_url,
        title = title,
        synopsis = synopsis,
        type = type,
        episodes = episodes,
        score = score,
        members = members
    )
}

internal fun MyAnime.toEntity(): MyAnimeEntity {
    return MyAnimeEntity(
        mal_id = mal_id,
        image_url = image_url,
        title = title,
        myScore = myScore,
        watchStatus = watchStatus
    )
}