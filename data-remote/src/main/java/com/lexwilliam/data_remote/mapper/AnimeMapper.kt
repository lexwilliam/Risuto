package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.search.SearchAnimeRepo
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonAnimeRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopAnimeRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import com.lexwilliam.data_remote.model.search.SearchAnimeResponse
import com.lexwilliam.data_remote.model.search.SearchResponse
import com.lexwilliam.data_remote.model.season.SeasonAnimeResponse
import com.lexwilliam.data_remote.model.season.SeasonResponse
import com.lexwilliam.data_remote.model.top.TopAnimeResponse
import com.lexwilliam.data_remote.model.top.TopResponse
import javax.inject.Inject

interface AnimeMapper {
    fun toRepo(search: SearchResponse): SearchRepo
    fun toRepo(searchAnime: SearchAnimeResponse): SearchAnimeRepo
    fun toRepo(top: TopResponse): TopRepo
    fun toRepo(topAnime: TopAnimeResponse): TopAnimeRepo
    fun toRepo(season: SeasonResponse): SeasonRepo
    fun toRepo(seasonAnime: SeasonAnimeResponse): SeasonAnimeRepo
}

class AnimeMapperImpl @Inject constructor(
    private val commonMapper: CommonMapper
): AnimeMapper {
    override fun toRepo(search: SearchResponse): SearchRepo =
        SearchRepo(search.request_hash, search.request_cached, search.request_cache_expiry, search.results.map { toRepo(it) }, search.last_page)


    override fun toRepo(searchAnime: SearchAnimeResponse): SearchAnimeRepo =
        SearchAnimeRepo(searchAnime.mal_id, searchAnime.url, searchAnime.image_url, searchAnime.title, searchAnime.airing, searchAnime.synopsis, searchAnime.type, searchAnime.episodes, searchAnime.score, searchAnime.start_date, searchAnime.end_date, searchAnime.members, searchAnime.rated)

    override fun toRepo(top: TopResponse): TopRepo =
        TopRepo(top.request_hash, top.request_cached, top.request_cache_expiry, top.top.map { toRepo(it) })


    override fun toRepo(topAnime: TopAnimeResponse): TopAnimeRepo =
        TopAnimeRepo(topAnime.mal_id, topAnime.rank, topAnime.title, topAnime.url, topAnime.image_url, topAnime.type, topAnime.episodes, topAnime.start_date, topAnime.end_date, topAnime.members, topAnime.score)

    override fun toRepo(season: SeasonResponse): SeasonRepo =
        SeasonRepo(season.request_hash, season.request_cached, season.request_cache_expiry, season.season_name, season.season_year, season.anime.map { toRepo(it) })

    override fun toRepo(seasonAnime: SeasonAnimeResponse): SeasonAnimeRepo =
        SeasonAnimeRepo(seasonAnime.airing_start, seasonAnime.continuing, seasonAnime.demographics.map { commonMapper.toRepo(it) }, seasonAnime.episodes, seasonAnime.explicit_genres, seasonAnime.genres.map { commonMapper.toRepo(it) }, seasonAnime.image_url, seasonAnime.kids, seasonAnime.licensors, seasonAnime.mal_id, seasonAnime.members, seasonAnime.producers.map { commonMapper.toRepo(it) }, seasonAnime.r18, seasonAnime.score, seasonAnime.source, seasonAnime.synopsis, seasonAnime.themes.map { commonMapper.toRepo(it) }, seasonAnime.title, seasonAnime.type, seasonAnime.url)
}