package com.example.risuto.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import com.example.risuto.domain.model.TopAnime
import com.example.risuto.presentation.model.custom.GridStylePresentation
import com.example.risuto.presentation.model.custom.RowStylePresentation
import com.example.risuto.presentation.model.TopAnimePresentation

internal fun TopAnime.toGrid(): GridStylePresentation {
    return GridStylePresentation(mal_id, image_url?: "", title, type, episodes?:0, score, members)
}

//internal fun List<TopAnimePresentation>.toGridList(): List<GridStylePresentation> {
//    val items = listOf<TopAnimePresentation>()
//    val animes = items.map { item -> item.toGrid() }
//    return animes
//}

internal fun SearchAnime.toRow(): RowStylePresentation {
    return RowStylePresentation(mal_id, image_url?: "", title, synopsis?: "", type, episodes?: 0, score, members)
}

//internal fun List<SearchAnimePresentation>.toRowList(): List<RowStylePresentation> {
//    val items = listOf<SearchAnimePresentation>()
//    val animes = items.map { item -> item.toRow() }
//    return animes
//}