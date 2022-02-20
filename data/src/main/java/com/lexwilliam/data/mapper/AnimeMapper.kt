package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.search.SearchAnimeRepo
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonAnimeRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopAnimeRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.season.SeasonAnime
import com.lexwilliam.domain.model.remote.top.Top
import com.lexwilliam.domain.model.remote.top.TopAnime
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import javax.inject.Inject

interface AnimeMapper {
    fun toDomain(search: SearchRepo): Search
    fun toDomain(searchAnime: SearchAnimeRepo): SearchAnime
    fun toDomain(top: TopRepo): Top
    fun toDomain(topAnime: TopAnimeRepo): TopAnime
    fun toDomain(season: SeasonRepo): Season
    fun toDomain(seasonAnime: SeasonAnimeRepo): SeasonAnime
    fun toDomain(userAnime: UserAnimeListRepo): UserAnimeList
}

class AnimeMapperImpl @Inject constructor(
    private val commonMapper: CommonMapper
): AnimeMapper {
    override fun toDomain(search: SearchRepo): Search =
        Search(search.request_hash, search.request_cached, search.request_cache_expiry, search.results.map { toDomain(it) }, search.last_page)

    override fun toDomain(searchAnime: SearchAnimeRepo): SearchAnime =
        SearchAnime(searchAnime.mal_id, searchAnime.url, searchAnime.image_url, searchAnime.title, searchAnime.airing, searchAnime.synopsis, searchAnime.type, searchAnime.episodes, searchAnime.score, searchAnime.start_date, searchAnime.end_date, searchAnime.members, searchAnime.rated)

    override fun toDomain(top: TopRepo): Top =
        Top(top.request_hash, top.request_cached, top.request_cache_expiry, top.top.map { toDomain(it) })


    override fun toDomain(topAnime: TopAnimeRepo): TopAnime =
        TopAnime(topAnime.mal_id, topAnime.rank, topAnime.title, topAnime.url, topAnime.image_url, topAnime.type, topAnime.episodes, topAnime.start_date, topAnime.end_date, topAnime.members, topAnime.score)

    override fun toDomain(season: SeasonRepo): Season =
        Season(season.request_hash, season.request_cached, season.request_cache_expiry, season.season_name, season.season_year, season.anime.map { toDomain(it) })

    override fun toDomain(seasonAnime: SeasonAnimeRepo): SeasonAnime =
        SeasonAnime(seasonAnime.airing_start, seasonAnime.continuing, seasonAnime.demographics.map { commonMapper.toDomain(it) }, seasonAnime.episodes, seasonAnime.explicit_genres, seasonAnime.genres.map { commonMapper.toDomain(it) }, seasonAnime.image_url, seasonAnime.kids, seasonAnime.licensors, seasonAnime.mal_id, seasonAnime.members, seasonAnime.producers.map { commonMapper.toDomain(it) }, seasonAnime.r18, seasonAnime.score, seasonAnime.source, seasonAnime.synopsis, seasonAnime.themes.map { commonMapper.toDomain(it) }, seasonAnime.title, seasonAnime.type, seasonAnime.url)

    override fun toDomain(userAnime: UserAnimeListRepo): UserAnimeList =
        UserAnimeList(userAnime.data.map { toDomain(it) })

    private fun toDomain(data: UserAnimeListRepo.Data): UserAnimeList.Data =
        UserAnimeList.Data(toDomain(data.listStatus), toDomain(data.node))

    private fun toDomain(listStatus: UserAnimeListRepo.Data.ListStatus): UserAnimeList.Data.ListStatus =
        UserAnimeList.Data.ListStatus(listStatus.isReWatching, listStatus.numWatchedEpisodes, listStatus.score, listStatus.status, listStatus.updatedAt)

    private fun toDomain(node: UserAnimeListRepo.Data.Node): UserAnimeList.Data.Node =
        UserAnimeList.Data.Node(node.id, node.numTotalEpisodes, toDomain(node.mainPicture?: UserAnimeListRepo.Data.Node.MainPicture("", "")), node.title)

    private fun toDomain(mainPicture: UserAnimeListRepo.Data.Node.MainPicture): UserAnimeList.Data.Node.MainPicture =
        UserAnimeList.Data.Node.MainPicture(mainPicture.large, mainPicture.medium)
}