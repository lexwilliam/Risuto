package com.example.risuto.data.local.mapper

import com.example.risuto.data.local.model.AnimeHistoryEntity
import com.example.risuto.data.local.model.MyAnimeEntity
import com.example.risuto.data.local.model.SearchHistoryEntity
import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.model.SearchHistory

internal fun SearchHistoryEntity.toDomain(): SearchHistory {
    return SearchHistory(query)
}

internal fun AnimeHistoryEntity.toDomain(): AnimeHistory {
    return AnimeHistory(mal_id, image_url, title, synopsis, type, episodes, score, members)
}

internal fun MyAnimeEntity.toDomain(): MyAnime {
    return MyAnime(mal_id, image_url, title, myScore, watchStatus?: WatchStatus.Default)
}