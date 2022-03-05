package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.risuto.model.WatchStatusPresentation
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.UserAnimeListPresentation
import javax.inject.Inject

interface AnimeMapper {
    fun toPresentation(anime: Anime): AnimePresentation
    fun toPresentation(data: Anime.Data): AnimePresentation.Data
    fun toPresentation(userAnime: UserAnimeList.Data): UserAnimeListPresentation.Data
}

class AnimeMapperImpl @Inject constructor(): AnimeMapper {

    override fun toPresentation(anime: Anime): AnimePresentation =
        AnimePresentation(anime.data.map { toPresentation(it) },
            toPresentation(anime.pagination)
        )

    override fun toPresentation(data: Anime.Data): AnimePresentation.Data =
        AnimePresentation.Data(
            toPresentation(data.aired), data.airing, data.background,
            toPresentation(data.broadcast), data.demographics.map { toPresentation(it) }, data.duration, data.episodes, data.explicit_genres.map { toPresentation(it) }, data.favorites, data.genres.map { toPresentation(it) },
            toPresentation(data.images), data.licensors.map { toPresentation(it) }, data.mal_id, data.members, data.popularity, data.producers.map { toPresentation(it) }, data.rank, data.rating, data.score, data.scored_by, data.season, data.source, data.status, data.studios.map { toPresentation(it) }, data.synopsis, data.themes.map { toPresentation(it) }, data.title, data.title_english, data.title_japanese, data.title_synonyms,
            toPresentation(data.trailer), data.type, data.url, data.year)

    override fun toPresentation(userAnime: UserAnimeList.Data): UserAnimeListPresentation.Data =
        UserAnimeListPresentation.Data(
            toPresentation(userAnime.listStatus),
            toPresentation(userAnime.node)
        )

    private fun toPresentation(pagination: Anime.Pagination): AnimePresentation.Pagination =
        AnimePresentation.Pagination(pagination.has_next_page, pagination.last_visible_page)

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

    private fun toPresentation(listStatus: UserAnimeList.Data.ListStatus): UserAnimeListPresentation.Data.ListStatus =
        UserAnimeListPresentation.Data.ListStatus(listStatus.isReWatching, listStatus.numWatchedEpisodes, listStatus.score, toPresentation(listStatus.status), listStatus.updatedAt)

    private fun toPresentation(node: UserAnimeList.Data.Node): UserAnimeListPresentation.Data.Node =
        UserAnimeListPresentation.Data.Node(node.id, node.numTotalEpisodes,
            toPresentation(node.mainPicture), node.title)

    private fun toPresentation(mainPicture: UserAnimeList.Data.Node.MainPicture): UserAnimeListPresentation.Data.Node.MainPicture =
        UserAnimeListPresentation.Data.Node.MainPicture(mainPicture.large, mainPicture.medium)

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