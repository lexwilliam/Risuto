package com.lexwilliam.data_local.mapper

import com.lexwilliam.data_local.model.AnimeHistoryEntity
import com.lexwilliam.data_local.model.MyAnimeEntity
import com.lexwilliam.data_local.model.SearchHistoryEntity
import com.lexwilliam.data_local.model.WatchStatus
import com.example.risuto.domain.model.history.AnimeHistory
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.model.history.SearchHistory

internal fun SearchHistoryEntity.toDomain(): SearchHistory {
    return SearchHistory(query)
}

internal fun AnimeHistoryEntity.toDomain(): AnimeHistory {
    return AnimeHistory(mal_id, image_url, title, synopsis, type, episodes, score, members)
}

internal fun MyAnimeEntity.toDomain(): MyAnime {
    return MyAnime(mal_id, image_url, title, myScore, watchStatus?: WatchStatus.PlanToWatch)
}