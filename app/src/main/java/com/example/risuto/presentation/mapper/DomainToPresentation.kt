package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation

internal fun SearchAnime.toPresentation(): SearchAnimePresentation {
    return SearchAnimePresentation(mal_id, url, image_url, title, airing, synopsis, type, episodes, score, start_date, end_date, members, rated)
}