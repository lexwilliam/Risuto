package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.domain.model.*
import com.example.risuto.presentation.model.*
import com.example.risuto.presentation.model.SearchHistoryPresentation

internal fun Anime.toPresentation(): AnimePresentation {
    return AnimePresentation(aired, airing, background, broadcast, duration, ending_themes, episodes, favorites, genres, image_url, licensors, mal_id, members, opening_themes, popularity, premiered, producers, rank, rating, related, request_cache_expiry, request_cached, request_hash, score, scored_by, source, status, studios, synopsis, title, title_english, title_japanese, title_synonyms, trailer_url, type, url)
}

internal fun CharacterStaff.toPresentation(): CharacterStaffPresentation {
    return CharacterStaffPresentation(characters, request_cache_expiry, request_cached, request_hash, staff)
}

internal fun SearchAnime.toPresentation(): AnimeListPresentation {
    return AnimeListPresentation(mal_id, image_url, title, synopsis, type, episodes, score, members)
}

internal fun SeasonArchive.toPresentation(): SeasonArchivePresentation {
    return SeasonArchivePresentation(request_hash, request_cached, request_cache_expiry, archive)
}

internal fun TopAnime.toPresentation(): AnimeListPresentation {
    return AnimeListPresentation(mal_id, image_url, title, "", type, episodes, score, members)
}

internal fun SeasonAnime.toPresentation(): AnimeListPresentation {
    return AnimeListPresentation(mal_id, image_url, title, "", type, episodes, score, members)
}

internal fun SearchHistory.toPresentation(): SearchHistoryPresentation {
    return SearchHistoryPresentation(query)
}

internal fun AnimeHistory.toPresentation(): AnimeHistoryPresentation {
    return AnimeHistoryPresentation(mal_id, title, image_url)
}