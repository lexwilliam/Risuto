package com.lexwilliam.risuto.mapper

import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.season.SeasonAnime
import com.lexwilliam.domain.model.remote.top.Top
import com.lexwilliam.domain.model.remote.top.TopAnime
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.risuto.model.AnimeListPresentation
import com.lexwilliam.risuto.model.local.WatchStatusPresentation
import com.lexwilliam.risuto.model.remote.AnimePresentation
import com.lexwilliam.risuto.model.remote.AnimeRequestPresentation
import javax.inject.Inject

interface AnimeMapper {
    fun toPresentation(search: Search): AnimeRequestPresentation
    fun toPresentation(searchAnime: SearchAnime): AnimeListPresentation
    fun toPresentation(top: Top): AnimeRequestPresentation
    fun toPresentation(topAnime: TopAnime): AnimeListPresentation
    fun toPresentation(anime: Anime): AnimePresentation
    fun toPresentation(season: Season): AnimeRequestPresentation
    fun toPresentation(seasonAnime: SeasonAnime): AnimeListPresentation
    fun toPresentation(userAnime: UserAnimeList.Data): AnimeListPresentation
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

    override fun toPresentation(searchAnime: SearchAnime): AnimeListPresentation =
        AnimeListPresentation(
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

    override fun toPresentation(topAnime: TopAnime): AnimeListPresentation =
        AnimeListPresentation(
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

    override fun toPresentation(anime: Anime): AnimePresentation =
        AnimePresentation(anime.data.map { toPresentation(it) },
            toPresentation(anime.pagination)
        )

    private fun toPresentation(pagination: Anime.Pagination): AnimePresentation.Pagination =
        AnimePresentation.Pagination(pagination.has_next_page, pagination.last_visible_page)

    private fun toPresentation(data: Anime.Data): AnimePresentation.Data =
        AnimePresentation.Data(
            toPresentation(data.aired), data.airing, data.background,
            toPresentation(data.broadcast), data.demographics.map { toPresentation(it) }, data.duration, data.episodes, data.explicit_genres.map { toPresentation(it) }, data.favorites, data.genres.map { toPresentation(it) },
            toPresentation(data.images), data.licensors.map { toPresentation(it) }, data.mal_id, data.members, data.popularity, data.producers.map { toPresentation(it) }, data.rank, data.rating, data.score, data.scored_by, data.season, data.source, data.status, data.studios.map { toPresentation(it) }, data.synopsis, data.themes.map { toPresentation(it) }, data.title, data.title_english, data.title_japanese, data.title_synonyms,
            toPresentation(data.trailer), data.type, data.url, data.year)

    private fun toPresentation(aired: Anime.Data.Aired): AnimePresentation.Data.Aired =
        AnimePresentation.Data.Aired(aired.from, toPresentation(aired.prop), aired.to)

    private fun toPresentation(prop: Anime.Data.Aired.Prop): AnimePresentation.Data.Aired.Prop =
        AnimePresentation.Data.Aired.Prop(
            toPresentation(prop.from), prop.string,
            toPresentation(prop.to)
        )

    private fun toPresentation(from: Anime.Data.Aired.Prop.From): AnimePresentation.Data.Aired.Prop.From =
        AnimePresentation.Data.Aired.Prop.From(from.day, from.month, from.year)

    private fun toPresentation(to: Anime.Data.Aired.Prop.To): AnimePresentation.Data.Aired.Prop.To =
        AnimePresentation.Data.Aired.Prop.To(to.day, to.month, to.year)

    private fun toPresentation(broadcast: Anime.Data.Broadcast): AnimePresentation.Data.Broadcast =
        AnimePresentation.Data.Broadcast(broadcast.day, broadcast.string, broadcast.time, broadcast.timezone)

    private fun toPresentation(demographic: Anime.Data.Demographic): AnimePresentation.Data.Demographic =
        AnimePresentation.Data.Demographic(demographic.mal_id, demographic.name, demographic.type, demographic.url)

    private fun toPresentation(genre: Anime.Data.ExplicitGenre): AnimePresentation.Data.ExplicitGenre =
        AnimePresentation.Data.ExplicitGenre(genre.mal_id, genre.name, genre.type, genre.url)

    private fun toPresentation(genre: Anime.Data.Genre): AnimePresentation.Data.Genre =
        AnimePresentation.Data.Genre(genre.mal_id, genre.name, genre.type, genre.url)

    private fun toPresentation(images: Anime.Data.Images): AnimePresentation.Data.Images =
        AnimePresentation.Data.Images(
            toPresentation(images.jpg),
            toPresentation(images.webp)
        )

    private fun toPresentation(jpg: Anime.Data.Images.Jpg): AnimePresentation.Data.Images.Jpg =
        AnimePresentation.Data.Images.Jpg(jpg.image_url, jpg.large_image_url, jpg.small_image_url)

    private fun toPresentation(webp: Anime.Data.Images.Webp): AnimePresentation.Data.Images.Webp =
        AnimePresentation.Data.Images.Webp(webp.image_url, webp.large_image_url, webp.small_image_url)

    private fun toPresentation(licensor: Anime.Data.Licensor): AnimePresentation.Data.Licensor =
        AnimePresentation.Data.Licensor(licensor.mal_id, licensor.name, licensor.type, licensor.url)

    private fun toPresentation(producer: Anime.Data.Producer): AnimePresentation.Data.Producer =
        AnimePresentation.Data.Producer(producer.mal_id, producer.name, producer.type, producer.url)

    private fun toPresentation(studio: Anime.Data.Studio): AnimePresentation.Data.Studio =
        AnimePresentation.Data.Studio(studio.mal_id, studio.name, studio.type, studio.url)

    private fun toPresentation(theme: Anime.Data.Theme): AnimePresentation.Data.Theme =
        AnimePresentation.Data.Theme(theme.mal_id, theme.name, theme.type, theme.url)

    private fun toPresentation(trailer: Anime.Data.Trailer): AnimePresentation.Data.Trailer =
        AnimePresentation.Data.Trailer(trailer.embed_url, trailer.url, trailer.youtube_id)


    override fun toPresentation(season: Season): AnimeRequestPresentation =
        AnimeRequestPresentation(
            request_hash = season.request_hash,
            request_cached = season.request_cached,
            request_cache_expiry = season.request_cache_expiry,
            season_name = season.season_name,
            season_year = season.season_year,
            anime = season.anime.map { toPresentation(it) }
        )

    override fun toPresentation(seasonAnime: SeasonAnime): AnimeListPresentation =
        AnimeListPresentation(
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

    override fun toPresentation(userAnime: UserAnimeList.Data): AnimeListPresentation =
        AnimeListPresentation(
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