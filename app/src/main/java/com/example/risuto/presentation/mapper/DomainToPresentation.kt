package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import com.example.risuto.domain.model.TopItem
import com.example.risuto.presentation.model.TopItemPresentation

internal fun SearchAnime.toPresentation(): SearchAnimePresentation {
    return SearchAnimePresentation(mal_id, url, image_url, title, airing, synopsis, type, episodes, score, start_date, end_date, members, rated)
}

internal fun TopItem.toPresentation(): TopItemPresentation {
    return TopItemPresentation(mal_id, rank, title, url, image_url, type, episodes, start_date, end_date, members, score)
}