package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.data.remote.model.list.SeasonAnimeResponse
import com.example.risuto.data.remote.model.list.request.RequestSeason
import com.example.risuto.domain.model.*
import com.example.risuto.domain.model.detail.*
import com.example.risuto.domain.model.history.AnimeHistory
import com.example.risuto.domain.model.history.SearchHistory
import com.example.risuto.presentation.model.*
import com.example.risuto.presentation.model.SearchHistoryPresentation
import com.example.risuto.presentation.model.detail.*

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

fun SeasonAnime.toPresentation(): AnimeListPresentation {
    return AnimeListPresentation(mal_id, image_url, title, "", type, episodes, score, members)
}

internal fun Season.toPresentation(): SeasonPresentation {
    return SeasonPresentation(request_hash, request_cached, request_cache_expiry, season_name, season_year, anime.map { it.toPresentation() })
}

internal fun SearchHistory.toPresentation(): SearchHistoryPresentation {
    return SearchHistoryPresentation(query)
}

internal fun AnimeHistory.toPresentation(): AnimeListPresentation {
    return AnimeListPresentation(mal_id, image_url, title, synopsis, type, episodes, score, members)
}

internal fun MyAnime.toPresentation(): MyAnimePresentation {
    return MyAnimePresentation(mal_id, image_url, title, myScore, watchStatus)
}

internal fun Episodes.toPresentation(): EpisodesPresentation {
    return EpisodesPresentation(episodes, episodes_last_page)
}

internal fun Forum.toPresentation(): ForumPresentation {
    return ForumPresentation(topics)
}

internal fun MoreInfo.toPresentation(): MoreInfoPresentation {
    return MoreInfoPresentation(moreinfo)
}

internal fun News.toPresentation(): NewsPresentation {
    return NewsPresentation(articles)
}

internal fun Pictures.toPresentation(): PicturesPresentation {
    return PicturesPresentation(pictures)
}

internal fun Recommendations.toPresentation(): RecommendationsPresentation {
    return RecommendationsPresentation(recommendations)
}

internal fun Reviews.toPresentation(): ReviewsPresentation {
    return ReviewsPresentation(reviews)
}

internal fun Stats.toPresentation(): StatsPresentation {
    return StatsPresentation(completed, dropped, on_hold, plan_to_watch, scores, total, watching)
}

internal fun Videos.toPresentation(): VideosPresentation {
    return VideosPresentation(episodes, promo)
}