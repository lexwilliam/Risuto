package com.chun2maru.risutomvvm.data.mapper

import com.chun2maru.risutomvvm.data.remote.model.SearchAnimeResponse
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.data.remote.model.TopItemResponse
import com.example.risuto.domain.model.TopItem

internal fun SearchAnimeResponse.toDomain(): SearchAnime {
    return SearchAnime(mal_id, url, image_url, title, airing, synopsis, type, episodes, score, start_date, end_date, members, rated)
}

internal fun TopItemResponse.toDomain(): TopItem {
    return TopItem(mal_id, rank, title, url, image_url, type, episodes, start_date, end_date, members, score)
}