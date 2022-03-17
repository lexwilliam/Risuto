package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.anime.SeasonListRepo
import com.lexwilliam.data_remote.model.anime.AnimeResponse
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data_remote.model.anime.SeasonListResponse
import com.lexwilliam.data_remote.model.user.UserAnimeListResponse
import javax.inject.Inject

interface AnimeMapper {
    fun toRepo(anime: AnimeResponse): AnimeRepo
    fun toRepo(data: AnimeResponse.Data): AnimeRepo.Data
    fun toRepo(userAnime: UserAnimeListResponse): UserAnimeListRepo
    fun toRepo(seasonList: SeasonListResponse): SeasonListRepo
}

class AnimeMapperImpl @Inject constructor(): AnimeMapper {

    override fun toRepo(anime: AnimeResponse): AnimeRepo =
        AnimeRepo(anime.data.map { toRepo(it) }, toRepo(anime.pagination))

    private fun toRepo(pagination: AnimeResponse.Pagination): AnimeRepo.Pagination =
        AnimeRepo.Pagination(pagination.has_next_page, pagination.last_visible_page)

    override fun toRepo(data: AnimeResponse.Data): AnimeRepo.Data =
        AnimeRepo.Data(toRepo(data.aired), data.airing, data.background?:"", toRepo(data.broadcast), data.demographics?.map { toRepo(it) } ?: emptyList(), data.duration, data.episodes?:0, data.explicit_genres?.map { toRepo(it) } ?: emptyList(), data.favorites, data.genres.map { toRepo(it) }, toRepo(data.images), data.licensors.map { toRepo(it) }, data.mal_id, data.members, data.popularity, data.producers.map { toRepo(it) }, data.rank?:-1, data.rating, data.score?:-1.0, data.scored_by?:-1.0, data.season?:"", data.source, data.status, data.studios.map { toRepo(it) }, data.synopsis?:"", data.themes?.map { toRepo(it) } ?: emptyList(), data.title, data.title_english?:"", data.title_japanese?:"", data.title_synonyms?: emptyList(), toRepo(data.trailer), data.type, data.url, data.year?:-1)

    private fun toRepo(aired: AnimeResponse.Data.Aired): AnimeRepo.Data.Aired =
        AnimeRepo.Data.Aired(aired.from?:"", toRepo(aired.prop), aired.to?:"")

    private fun toRepo(prop: AnimeResponse.Data.Aired.Prop): AnimeRepo.Data.Aired.Prop =
        AnimeRepo.Data.Aired.Prop(toRepo(prop.from), prop.string?:"", toRepo(prop.to))

    private fun toRepo(from: AnimeResponse.Data.Aired.Prop.From): AnimeRepo.Data.Aired.Prop.From =
        AnimeRepo.Data.Aired.Prop.From(from.day?:-1, from.month?:-1, from.year?:-1)

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

    override fun toRepo(userAnime: UserAnimeListResponse): UserAnimeListRepo =
        UserAnimeListRepo(userAnime.data.map { toRepo(it) })

    private fun toRepo(data: UserAnimeListResponse.Data): UserAnimeListRepo.Data =
        UserAnimeListRepo.Data(toRepo(data.listStatus), toRepo(data.node))

    private fun toRepo(listStatus: UserAnimeListResponse.Data.ListStatus): UserAnimeListRepo.Data.ListStatus =
        UserAnimeListRepo.Data.ListStatus(listStatus.isReWatching, listStatus.numWatchedEpisodes, listStatus.score, listStatus.status?:"", listStatus.updatedAt)

    private fun toRepo(node: UserAnimeListResponse.Data.Node): UserAnimeListRepo.Data.Node =
        UserAnimeListRepo.Data.Node(node.id, node.numTotalEpisodes, toRepo(node.mainPicture?: UserAnimeListResponse.Data.Node.MainPicture("", "")), node.title)

    private fun toRepo(mainPicture: UserAnimeListResponse.Data.Node.MainPicture): UserAnimeListRepo.Data.Node.MainPicture =
        UserAnimeListRepo.Data.Node.MainPicture(mainPicture.large?:"", mainPicture.medium)

    override fun toRepo(seasonList: SeasonListResponse): SeasonListRepo =
        SeasonListRepo(seasonList.data.map { toRepo(it) })

    private fun toRepo(data: SeasonListResponse.Data): SeasonListRepo.Data =
        SeasonListRepo.Data(data.year, data.seasons)
}