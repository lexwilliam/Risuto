package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.domain.model.Anime
import com.example.risuto.domain.model.TopAnime
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.model.RowStylePresentation

internal fun Anime.toPresentation(): AnimePresentation {
    return AnimePresentation(aired, airing, background?: "" , broadcast, duration, ending_themes, episodes?: 0, favorites, genres, image_url?: "", licensors, mal_id, members, opening_themes, popularity, premiered, producers, rank, rating, related, request_cache_expiry, request_cached, request_hash, score, scored_by, source, status, studios, synopsis?: "", title, title_english, title_japanese, title_synonyms, trailer_url?: "", type, url)
}

internal fun SearchAnime.toRow(): RowStylePresentation {
    return RowStylePresentation(mal_id, image_url?: "", title, synopsis?: "", type, episodes?: 0, score, members)
}

internal fun TopAnime.toGrid(): GridStylePresentation {
    return GridStylePresentation(mal_id, image_url?: "", title, type, episodes?:0, score, members)
}