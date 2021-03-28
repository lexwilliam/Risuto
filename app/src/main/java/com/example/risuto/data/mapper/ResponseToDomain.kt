package com.chun2maru.risutomvvm.data.mapper

import com.chun2maru.risutomvvm.data.remote.model.SearchAnimeResponse
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.data.remote.model.AnimeResponse
import com.example.risuto.data.remote.model.TopAnimeResponse
import com.example.risuto.domain.model.Anime
import com.example.risuto.domain.model.TopAnime

internal fun SearchAnimeResponse.toDomain(): SearchAnime {
    return SearchAnime(mal_id, url, image_url, title, airing, synopsis, type, episodes, score, start_date, end_date, members, rated)
}

internal fun TopAnimeResponse.toDomain(): TopAnime {
    return TopAnime(mal_id, rank, title, url, image_url, type, episodes, start_date, end_date, members, score)
}

internal fun AnimeResponse.toDomain(): Anime {
    return Anime(aired, airing, background, broadcast, duration, ending_themes, episodes, favorites, genres, image_url?: "", licensors, mal_id, members, opening_themes, popularity, premiered, producers, rank, rating, related, request_cache_expiry, request_cached, request_hash, score, scored_by, source, status, studios, synopsis?: "", title, title_english, title_japanese, title_synonyms, trailer_url, type, url)
}