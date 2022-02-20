package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.season.SeasonAnime
import com.lexwilliam.domain.model.remote.top.Top
import com.lexwilliam.domain.model.remote.top.TopAnime
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.local.WatchStatusPresentation
import com.lexwilliam.risuto.model.remote.AnimeRequestPresentation
import javax.inject.Inject

interface AnimeMapper {
    fun toPresentation(search: Search): AnimeRequestPresentation
    fun toPresentation(searchAnime: SearchAnime): AnimePresentation
    fun toPresentation(top: Top): AnimeRequestPresentation
    fun toPresentation(topAnime: TopAnime): AnimePresentation
    fun toPresentation(season: Season): AnimeRequestPresentation
    fun toPresentation(seasonAnime: SeasonAnime): AnimePresentation
    fun toPresentation(userAnime: UserAnimeList.Data): AnimePresentation
}

class AnimeMapperImpl @Inject constructor(
    private val commonMapper: CommonMapper
): AnimeMapper {
    override fun toPresentation(search: Search): AnimeRequestPresentation =
        AnimeRequestPresentation(
            request_hash = search.request_hash,
            request_cached = search.request_cached,
            request_cache_expiry = search.request_cache_expiry,
            anime = search.results.map { toPresentation(it) },
            last_page = search.last_page
        )

    override fun toPresentation(searchAnime: SearchAnime): AnimePresentation =
        AnimePresentation(
            mal_id = searchAnime.mal_id,
            url = searchAnime.url,
            image_url = searchAnime.image_url,
            title = searchAnime.title,
            airing = searchAnime.airing,
            synopsis = searchAnime.synopsis,
            type = searchAnime.type,
            episodes = searchAnime.episodes,
            score = searchAnime.score,
            start_date = searchAnime.start_date,
            end_date = searchAnime.end_date,
            members = searchAnime.members,
            rated = searchAnime.rated
        )

    override fun toPresentation(top: Top): AnimeRequestPresentation =
        AnimeRequestPresentation(
            request_hash = top.request_hash,
            request_cached = top.request_cached,
            request_cache_expiry = top.request_cache_expiry,
            anime = top.top.map { toPresentation(it) }
        )

    override fun toPresentation(topAnime: TopAnime): AnimePresentation =
        AnimePresentation(
            mal_id = topAnime.mal_id,
            rank = topAnime.rank,
            title = topAnime.title,
            url = topAnime.url,
            image_url = topAnime.image_url,
            type = topAnime.type,
            episodes = topAnime.episodes,
            start_date = topAnime.start_date,
            end_date = topAnime.end_date,
            members = topAnime.members,
            score = topAnime.score
        )

    override fun toPresentation(season: Season): AnimeRequestPresentation =
        AnimeRequestPresentation(
            request_hash = season.request_hash,
            request_cached = season.request_cached,
            request_cache_expiry = season.request_cache_expiry,
            season_name = season.season_name,
            season_year = season.season_year,
            anime = season.anime.map { toPresentation(it) }
        )

    override fun toPresentation(seasonAnime: SeasonAnime): AnimePresentation =
        AnimePresentation(
            airing_start = seasonAnime.airing_start,
            continuing = seasonAnime.continuing,
            demographics = seasonAnime.demographics.map { commonMapper.toPresentation(it) },
            episodes = seasonAnime.episodes,
            explicit_genres = seasonAnime.explicit_genres,
            genres = seasonAnime.genres.map { commonMapper.toPresentation(it) },
            image_url = seasonAnime.image_url,
            kids = seasonAnime.kids,
            licensors = seasonAnime.licensors,
            mal_id = seasonAnime.mal_id,
            members = seasonAnime.members,
            producers = seasonAnime.producers.map { commonMapper.toPresentation(it) },
            r18 = seasonAnime.r18,
            score = seasonAnime.score,
            source = seasonAnime.source,
            synopsis = seasonAnime.synopsis,
            themes = seasonAnime.themes.map { commonMapper.toPresentation(it) },
            title = seasonAnime.title,
            type = seasonAnime.type,
            url = seasonAnime.url
        )

    override fun toPresentation(userAnime: UserAnimeList.Data): AnimePresentation =
        AnimePresentation(
            mal_id = userAnime.node.id,
            title = userAnime.node.title,
            image_url = userAnime.node.mainPicture?.medium,
            watch_status = toPresentation(userAnime.listStatus.status?:""),
            my_score = userAnime.listStatus.score
        )

    private fun toPresentation(status: String): WatchStatusPresentation {
        return when(status) {
            "plan_to_watch" -> WatchStatusPresentation.PlanToWatch
            "watching" -> WatchStatusPresentation.Watching
            "completed" -> WatchStatusPresentation.Completed
            "on_hold" -> WatchStatusPresentation.OnHold
            "dropped" -> WatchStatusPresentation.Dropped
            else -> WatchStatusPresentation.Default
        }
    }
}