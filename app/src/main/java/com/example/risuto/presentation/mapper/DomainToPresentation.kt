package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import com.example.risuto.domain.model.Anime
import com.example.risuto.domain.model.TopAnime
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.TopAnimePresentation

internal fun SearchAnime.toPresentation(): SearchAnimePresentation {
    return SearchAnimePresentation(mal_id, url, image_url?: "", title, airing, synopsis?: "", type, episodes?: 0, score, start_date?: "", end_date?: "", members, rated?: "")
}

internal fun TopAnime.toPresentation(): TopAnimePresentation {
    return TopAnimePresentation(mal_id, rank, title, url, image_url?: "", type, episodes?: 0, start_date?: "", end_date?: "", members, score)
}

internal fun Anime.toPresentation(): AnimePresentation {
    return AnimePresentation(aired, airing, background, broadcast, duration, ending_themes, episodes, favorites, genres, image_url, licensors, mal_id, members, opening_themes, popularity, premiered, producers, rank, rating, related, request_cache_expiry, request_cached, request_hash, score, scored_by, source, status, studios, synopsis, title, title_english, title_japanese, title_synonyms, trailer_url, type, url)
}