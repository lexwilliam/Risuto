package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import javax.inject.Inject

interface AnimeMapper {
    fun toDomain(anime: AnimeRepo): Anime
    fun toDomain(data: AnimeRepo.Data): Anime.Data
    fun toDomain(userAnime: UserAnimeListRepo): UserAnimeList
}

class AnimeMapperImpl @Inject constructor(): AnimeMapper {
    override fun toDomain(anime: AnimeRepo): Anime =
        Anime(anime.data.map { toDomain(it) }, toDomain(anime.pagination))

    private fun toDomain(pagination: AnimeRepo.Pagination): Anime.Pagination =
        Anime.Pagination(pagination.has_next_page, pagination.last_visible_page)

    override fun toDomain(data: AnimeRepo.Data): Anime.Data =
        Anime.Data(toDomain(data.aired), data.airing, data.background, toDomain(data.broadcast), data.demographics.map { toDomain(it) }, data.duration, data.episodes, data.explicit_genres.map { toDomain(it) }, data.favorites, data.genres.map { toDomain(it) }, toDomain(data.images), data.licensors.map { toDomain(it) }, data.mal_id, data.members, data.popularity, data.producers.map { toDomain(it) }, data.rank, data.rating, data.score, data.scored_by, data.season, data.source, data.status, data.studios.map { toDomain(it) }, data.synopsis, data.themes.map { toDomain(it) }, data.title, data.title_english, data.title_japanese, data.title_synonyms, toDomain(data.trailer), data.type, data.url, data.year)

    private fun toDomain(aired: AnimeRepo.Data.Aired): Anime.Data.Aired =
        Anime.Data.Aired(aired.from, toDomain(aired.prop), aired.to)

    private fun toDomain(prop: AnimeRepo.Data.Aired.Prop): Anime.Data.Aired.Prop =
        Anime.Data.Aired.Prop(toDomain(prop.from), prop.string, toDomain(prop.to))

    private fun toDomain(from: AnimeRepo.Data.Aired.Prop.From): Anime.Data.Aired.Prop.From =
        Anime.Data.Aired.Prop.From(from.day, from.month, from.year)

    private fun toDomain(to: AnimeRepo.Data.Aired.Prop.To): Anime.Data.Aired.Prop.To =
        Anime.Data.Aired.Prop.To(to.day, to.month, to.year)

    private fun toDomain(broadcast: AnimeRepo.Data.Broadcast): Anime.Data.Broadcast =
        Anime.Data.Broadcast(broadcast.day, broadcast.string, broadcast.time, broadcast.timezone)

    private fun toDomain(demographic: AnimeRepo.Data.Demographic): Anime.Data.Demographic =
        Anime.Data.Demographic(demographic.mal_id, demographic.name, demographic.type, demographic.url)

    private fun toDomain(genre: AnimeRepo.Data.ExplicitGenre): Anime.Data.ExplicitGenre =
        Anime.Data.ExplicitGenre(genre.mal_id, genre.name, genre.type, genre.url)

    private fun toDomain(genre: AnimeRepo.Data.Genre): Anime.Data.Genre =
        Anime.Data.Genre(genre.mal_id, genre.name, genre.type, genre.url)

    private fun toDomain(images: AnimeRepo.Data.Images): Anime.Data.Images =
        Anime.Data.Images(toDomain(images.jpg), toDomain(images.webp))

    private fun toDomain(jpg: AnimeRepo.Data.Images.Jpg): Anime.Data.Images.Jpg =
        Anime.Data.Images.Jpg(jpg.image_url, jpg.large_image_url, jpg.small_image_url)

    private fun toDomain(webp: AnimeRepo.Data.Images.Webp): Anime.Data.Images.Webp =
        Anime.Data.Images.Webp(webp.image_url, webp.large_image_url, webp.small_image_url)

    private fun toDomain(licensor: AnimeRepo.Data.Licensor): Anime.Data.Licensor =
        Anime.Data.Licensor(licensor.mal_id, licensor.name, licensor.type, licensor.url)

    private fun toDomain(producer: AnimeRepo.Data.Producer): Anime.Data.Producer =
        Anime.Data.Producer(producer.mal_id, producer.name, producer.type, producer.url)

    private fun toDomain(studio: AnimeRepo.Data.Studio): Anime.Data.Studio =
        Anime.Data.Studio(studio.mal_id, studio.name, studio.type, studio.url)

    private fun toDomain(theme: AnimeRepo.Data.Theme): Anime.Data.Theme =
        Anime.Data.Theme(theme.mal_id, theme.name, theme.type, theme.url)

    private fun toDomain(trailer: AnimeRepo.Data.Trailer): Anime.Data.Trailer =
        Anime.Data.Trailer(trailer.embed_url, trailer.url, trailer.youtube_id)

    override fun toDomain(userAnime: UserAnimeListRepo): UserAnimeList =
        UserAnimeList(userAnime.data.map { this.toDomain(it) })

    private fun toDomain(data: UserAnimeListRepo.Data): UserAnimeList.Data =
        UserAnimeList.Data(
            this.toDomain(data.listStatus),
            this.toDomain(data.node)
        )

    private fun toDomain(listStatus: UserAnimeListRepo.Data.ListStatus): UserAnimeList.Data.ListStatus =
        UserAnimeList.Data.ListStatus(listStatus.isReWatching, listStatus.numWatchedEpisodes, listStatus.score, listStatus.status, listStatus.updatedAt)

    private fun toDomain(node: UserAnimeListRepo.Data.Node): UserAnimeList.Data.Node =
        UserAnimeList.Data.Node(node.id, node.numTotalEpisodes,
            this.toDomain(node.mainPicture), node.title)

    private fun toDomain(mainPicture: UserAnimeListRepo.Data.Node.MainPicture): UserAnimeList.Data.Node.MainPicture =
        UserAnimeList.Data.Node.MainPicture(mainPicture.large, mainPicture.medium)
}