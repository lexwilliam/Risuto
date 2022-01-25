package com.lexwilliam.risutov2.mapper

import com.example.risuto.domain.model.history.AnimeHistory
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.model.history.SearchHistory
import com.lexwilliam.risutov2.model.detail.AnimePresentation
import com.lexwilliam.risutov2.model.MyAnimePresentation
import com.lexwilliam.risutov2.model.SearchHistoryPresentation

internal fun SearchHistoryPresentation.toDomain(): SearchHistory {
    return SearchHistory(query)
}

internal fun AnimePresentation.toDomain(): AnimeHistory {
    return AnimeHistory(mal_id, image_url, title, synopsis, type, episodes, score, members)
}

internal fun MyAnimePresentation.toDomain(): MyAnime {
    return MyAnime(mal_id, image_url, title, myScore, watchStatus)
}