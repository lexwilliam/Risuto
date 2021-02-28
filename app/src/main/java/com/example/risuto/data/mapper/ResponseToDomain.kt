package com.chun2maru.risutomvvm.data.mapper

import com.chun2maru.risutomvvm.data.remote.AnimeService
import com.chun2maru.risutomvvm.data.remote.model.SearchAnimeResponse
import com.chun2maru.risutomvvm.domain.model.SearchAnime

internal fun SearchAnimeResponse.toDomain(): SearchAnime {
    return SearchAnime(mal_id, url, image_url, title, airing, synopsis, type, episodes, score, start_date, end_date, members, rated)
}