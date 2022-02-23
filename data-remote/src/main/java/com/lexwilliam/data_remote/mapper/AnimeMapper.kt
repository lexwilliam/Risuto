package com.lexwilliam.data_remote.mapper

import androidx.annotation.AnimRes
import com.lexwilliam.data.model.common.jikan.DemographicRepo
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.search.SearchAnimeRepo
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonAnimeRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopAnimeRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data_remote.model.anime.AnimeResponse
import com.lexwilliam.data_remote.model.search.SearchAnimeResponse
import com.lexwilliam.data_remote.model.search.SearchResponse
import com.lexwilliam.data_remote.model.season.SeasonAnimeResponse
import com.lexwilliam.data_remote.model.season.SeasonResponse
import com.lexwilliam.data_remote.model.top.TopAnimeResponse
import com.lexwilliam.data_remote.model.top.TopResponse
import com.lexwilliam.data_remote.model.user.UserAnimeListResponse
import javax.inject.Inject

interface AnimeMapper {
    fun toRepo(search: SearchResponse): SearchRepo
    fun toRepo(searchAnime: SearchAnimeResponse): SearchAnimeRepo
    fun toRepo(top: TopResponse): TopRepo
    fun toRepo(anime: AnimeResponse): AnimeRepo
    fun toRepo(topAnime: TopAnimeResponse): TopAnimeRepo
    fun toRepo(season: SeasonResponse): SeasonRepo
    fun toRepo(seasonAnime: SeasonAnimeResponse): SeasonAnimeRepo
    fun toRepo(userAnime: UserAnimeListResponse): UserAnimeListRepo
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

    override fun toRepo(anime: AnimeResponse): AnimeRepo =
        AnimeRepo(anime.data.map { toRepo(it) }, toRepo(anime.pagination))

    private fun toRepo(pagination: AnimeResponse.Pagination): AnimeRepo.Pagination =
        AnimeRepo.Pagination(pagination.has_next_page, pagination.last_visible_page)

    private fun toRepo(data: AnimeResponse.Data): AnimeRepo.Data =
        AnimeRepo.Data(toRepo(data.aired), data.airing, data.background?:"", toRepo(data.broadcast), data.demographics.map { toRepo(it) }, data.duration, data.episodes?:0, data.explicit_genres.map { toRepo(it) }, data.favorites, data.genres.map { toRepo(it) }, toRepo(data.images), data.licensors.map { toRepo(it) }, data.mal_id, data.members, data.popularity, data.producers.map { toRepo(it) }, data.rank, data.rating, data.score, data.scored_by, data.season?:"", data.source, data.status, data.studios.map { toRepo(it) }, data.synopsis, data.themes.map { toRepo(it) }, data.title, data.title_english?:"", data.title_japanese?:"", data.title_synonyms?: emptyList(), toRepo(data.trailer), data.type, data.url, data.year?:-1)

    private fun toRepo(aired: AnimeResponse.Data.Aired): AnimeRepo.Data.Aired =
        AnimeRepo.Data.Aired(aired.from, toRepo(aired.prop), aired.to?:"")

    private fun toRepo(prop: AnimeResponse.Data.Aired.Prop): AnimeRepo.Data.Aired.Prop =
        AnimeRepo.Data.Aired.Prop(toRepo(prop.from), prop.string?:"", toRepo(prop.to))

    private fun toRepo(from: AnimeResponse.Data.Aired.Prop.From): AnimeRepo.Data.Aired.Prop.From =
        AnimeRepo.Data.Aired.Prop.From(from.day, from.month, from.year)

    private fun toRepo(to: AnimeResponse.Data.Aired.Prop.To): AnimeRepo.Data.Aired.Prop.To =
        AnimeRepo.Data.Aired.Prop.To(to.day?:-1, to.month?:-1, to.year?:-1)

    private fun toRepo(broadcast: AnimeResponse.Data.Broadcast): AnimeRepo.Data.Broadcast =
        AnimeRepo.Data.Broadcast(broadcast.day?:"", broadcast.string?:"", broadcast.time?:"", broadcast.timezone?:"")

    private fun toRepo(demographic: AnimeResponse.Data.Demographic): AnimeRepo.Data.Demographic =
        AnimeRepo.Data.Demographic(demographic.mal_id, demographic.name, demographic.type, demographic.url)

    private fun toRepo(genre: AnimeResponse.Data.ExplicitGenre): AnimeRepo.Data.ExplicitGenre =
        AnimeRepo.Data.ExplicitGenre(genre.mal_id, genre.name, genre.type, genre.url)

    private fun toRepo(genre: AnimeResponse.Data.Genre): AnimeRepo.Data.Genre =
        AnimeRepo.Data.Genre(genre.mal_id, genre.name, genre.type, genre.url)

    private fun toRepo(images: AnimeResponse.Data.Images): AnimeRepo.Data.Images =
        AnimeRepo.Data.Images(toRepo(images.jpg), toRepo(images.webp))

    private fun toRepo(jpg: AnimeResponse.Data.Images.Jpg): AnimeRepo.Data.Images.Jpg =
        AnimeRepo.Data.Images.Jpg(jpg.image_url, jpg.large_image_url, jpg.small_image_url)

    private fun toRepo(webp: AnimeResponse.Data.Images.Webp): AnimeRepo.Data.Images.Webp =
        AnimeRepo.Data.Images.Webp(webp.image_url, webp.large_image_url, webp.small_image_url)

    private fun toRepo(licensor: AnimeResponse.Data.Licensor): AnimeRepo.Data.Licensor =
        AnimeRepo.Data.Licensor(licensor.mal_id, licensor.name, licensor.type, licensor.url)

    private fun toRepo(producer: AnimeResponse.Data.Producer): AnimeRepo.Data.Producer =
        AnimeRepo.Data.Producer(producer.mal_id, producer.name, producer.type, producer.url)

    private fun toRepo(studio: AnimeResponse.Data.Studio): AnimeRepo.Data.Studio =
        AnimeRepo.Data.Studio(studio.mal_id, studio.name, studio.type, studio.url)

    private fun toRepo(theme: AnimeResponse.Data.Theme): AnimeRepo.Data.Theme =
        AnimeRepo.Data.Theme(theme.mal_id, theme.name, theme.type, theme.url)

    private fun toRepo(trailer: AnimeResponse.Data.Trailer): AnimeRepo.Data.Trailer =
        AnimeRepo.Data.Trailer(trailer.embed_url?:"", trailer.url?:"", trailer.youtube_id?:"")

    override fun toRepo(topAnime: TopAnimeResponse): TopAnimeRepo =
        TopAnimeRepo(topAnime.mal_id, topAnime.rank, topAnime.title, topAnime.url, topAnime.image_url, topAnime.type, topAnime.episodes, topAnime.start_date, topAnime.end_date, topAnime.members, topAnime.score)

    override fun toRepo(season: SeasonResponse): SeasonRepo =
        SeasonRepo(season.request_hash, season.request_cached, season.request_cache_expiry, season.season_name, season.season_year, season.anime.map { toRepo(it) })

    override fun toRepo(seasonAnime: SeasonAnimeResponse): SeasonAnimeRepo =
        SeasonAnimeRepo(seasonAnime.airing_start, seasonAnime.continuing, seasonAnime.demographics.map { commonMapper.toRepo(it) }, seasonAnime.episodes, seasonAnime.explicit_genres, seasonAnime.genres.map { commonMapper.toRepo(it) }, seasonAnime.image_url, seasonAnime.kids, seasonAnime.licensors, seasonAnime.mal_id, seasonAnime.members, seasonAnime.producers.map { commonMapper.toRepo(it) }, seasonAnime.r18, seasonAnime.score, seasonAnime.source, seasonAnime.synopsis, seasonAnime.themes.map { commonMapper.toRepo(it) }, seasonAnime.title, seasonAnime.type, seasonAnime.url)

    override fun toRepo(userAnime: UserAnimeListResponse): UserAnimeListRepo =
        UserAnimeListRepo(userAnime.data.map { toRepo(it) })

    private fun toRepo(data: UserAnimeListResponse.Data): UserAnimeListRepo.Data =
        UserAnimeListRepo.Data(toRepo(data.listStatus), toRepo(data.node))

    private fun toRepo(listStatus: UserAnimeListResponse.Data.ListStatus): UserAnimeListRepo.Data.ListStatus =
        UserAnimeListRepo.Data.ListStatus(listStatus.isReWatching, listStatus.numWatchedEpisodes, listStatus.score, listStatus.status, listStatus.updatedAt)

    private fun toRepo(node: UserAnimeListResponse.Data.Node): UserAnimeListRepo.Data.Node =
        UserAnimeListRepo.Data.Node(node.id, node.numTotalEpisodes, toRepo(node.mainPicture?: UserAnimeListResponse.Data.Node.MainPicture("", "")), node.title)

    private fun toRepo(mainPicture: UserAnimeListResponse.Data.Node.MainPicture): UserAnimeListRepo.Data.Node.MainPicture =
        UserAnimeListRepo.Data.Node.MainPicture(mainPicture.large, mainPicture.medium)
}