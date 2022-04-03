package com.lexwilliam.risuto.mapper

import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data.model.remote.anime.AnimeVideosRepo
import com.lexwilliam.domain.model.remote.anime.AnimeCharacters
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.model.remote.anime.AnimeVideos
import com.lexwilliam.risuto.model.AnimeCharactersPresentation
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.model.AnimeVideosPresentation
import javax.inject.Inject

interface DetailMapper {
    fun toPresentation(detail: AnimeDetail): AnimeDetailPresentation
    fun toPresentation(characters: AnimeCharacters): AnimeCharactersPresentation
    fun toPresentation(videos: AnimeVideos): AnimeVideosPresentation
}

class DetailMapperImpl @Inject constructor(): DetailMapper {
    override fun toPresentation(detail: AnimeDetail): AnimeDetailPresentation =
        AnimeDetailPresentation(toPresentation(detail.alternative_titles), detail.average_episode_duration, detail.background, toPresentation(detail.broadcast), detail.created_at, detail.end_date, detail.genres.map { toPresentation(it) }, detail.id, toPresentation(detail.main_picture), detail.mean, detail.media_type, toPresentation(detail.my_list_status), detail.nsfw, detail.num_episodes, detail.num_list_users, detail.num_scoring_users, detail.pictures.map { toPresentation(it) }, detail.popularity, detail.rank, detail.rating, detail.recommendations.map { toPresentation(it) }, detail.related_anime.map { toPresentation(it) }, detail.related_manga, detail.source, detail.start_date, toPresentation(detail.start_season), toPresentation(detail.statistics), detail.status, detail.studios.map { toPresentation(it) }, detail.synopsis, detail.title, detail.updated_at)

    override fun toPresentation(characters: AnimeCharacters): AnimeCharactersPresentation =
        AnimeCharactersPresentation(characters.data.map { toPresentation(it) })

    override fun toPresentation(videos: AnimeVideos): AnimeVideosPresentation =
        AnimeVideosPresentation(toPresentation(videos.data))

    private fun toPresentation(alter: AnimeDetail.AlternativeTitles): AnimeDetailPresentation.AlternativeTitles =
        AnimeDetailPresentation.AlternativeTitles(alter.en, alter.ja, alter.synonyms)

    private fun toPresentation(broadcast: AnimeDetail.Broadcast): AnimeDetailPresentation.Broadcast =
        AnimeDetailPresentation.Broadcast(broadcast.day_of_the_week, broadcast.start_time)

    private fun toPresentation(genre: AnimeDetail.Genre): AnimeDetailPresentation.Genre =
        AnimeDetailPresentation.Genre(genre.id, genre.name)

    private fun toPresentation(mainPicture: AnimeDetail.MainPicture): AnimeDetailPresentation.MainPicture =
        AnimeDetailPresentation.MainPicture(mainPicture.large, mainPicture.medium)

    private fun toPresentation(status: AnimeDetail.MyListStatus): AnimeDetailPresentation.MyListStatus =
        AnimeDetailPresentation.MyListStatus(status.is_rewatching, status.num_episodes_watched, status.score, status.status, status.updated_at)

    private fun toPresentation(node: AnimeDetail.Node): AnimeDetailPresentation.Node =
        AnimeDetailPresentation.Node(node.id, toPresentation(node.main_picture), node.title)

    private fun toPresentation(picture: AnimeDetail.Picture): AnimeDetailPresentation.Picture =
        AnimeDetailPresentation.Picture(picture.large, picture.medium)

    private fun toPresentation(recommendation: AnimeDetail.Recommendation): AnimeDetailPresentation.Recommendation =
        AnimeDetailPresentation.Recommendation(toPresentation(recommendation.node), recommendation.num_recommendations)

    private fun toPresentation(relatedAnime: AnimeDetail.RelatedAnime): AnimeDetailPresentation.RelatedAnime =
        AnimeDetailPresentation.RelatedAnime(toPresentation(relatedAnime.node), relatedAnime.relation_type, relatedAnime.relation_type_formatted)

    private fun toPresentation(startSeason: AnimeDetail.StartSeason): AnimeDetailPresentation.StartSeason =
        AnimeDetailPresentation.StartSeason(startSeason.season, startSeason.year)

    private fun toPresentation(statistics: AnimeDetail.Statistics): AnimeDetailPresentation.Statistics =
        AnimeDetailPresentation.Statistics(statistics.num_list_users, toPresentation(statistics.status))

    private fun toPresentation(status: AnimeDetail.Status): AnimeDetailPresentation.Status =
        AnimeDetailPresentation.Status(status.completed, status.dropped, status.on_hold, status.plan_to_watch, status.watching)

    private fun toPresentation(studio: AnimeDetail.Studio): AnimeDetailPresentation.Studio =
        AnimeDetailPresentation.Studio(studio.id, studio.name)

    private fun toPresentation(data: AnimeCharacters.Data): AnimeCharactersPresentation.Data =
        AnimeCharactersPresentation.Data(toPresentation(data.character), data.role, data.voice_actors.map { toPresentation(it) })

    private fun toPresentation(character: AnimeCharacters.Data.Character): AnimeCharactersPresentation.Data.Character =
        AnimeCharactersPresentation.Data.Character(toPresentation(character.images), character.mal_id, character.name, character.url)

    private fun toPresentation(images: AnimeCharacters.Data.Character.Images): AnimeCharactersPresentation.Data.Character.Images =
        AnimeCharactersPresentation.Data.Character.Images(toPresentation(images.jpg), toPresentation(images.webp))

    private fun toPresentation(jpg: AnimeCharacters.Data.Character.Images.Jpg): AnimeCharactersPresentation.Data.Character.Images.Jpg =
        AnimeCharactersPresentation.Data.Character.Images.Jpg(jpg.image_url, jpg.small_image_url)

    private fun toPresentation(webp: AnimeCharacters.Data.Character.Images.Webp): AnimeCharactersPresentation.Data.Character.Images.Webp =
        AnimeCharactersPresentation.Data.Character.Images.Webp(webp.image_url, webp.small_image_url)

    private fun toPresentation(va: AnimeCharacters.Data.VoiceActor): AnimeCharactersPresentation.Data.VoiceActor =
        AnimeCharactersPresentation.Data.VoiceActor(va.language, toPresentation(va.person))

    private fun toPresentation(person: AnimeCharacters.Data.VoiceActor.Person): AnimeCharactersPresentation.Data.VoiceActor.Person =
        AnimeCharactersPresentation.Data.VoiceActor.Person(toPresentation(person.images), person.mal_id, person.name, person.url)

    private fun toPresentation(images: AnimeCharacters.Data.VoiceActor.Person.Images): AnimeCharactersPresentation.Data.VoiceActor.Person.Images =
        AnimeCharactersPresentation.Data.VoiceActor.Person.Images(toPresentation(images.jpg))

    private fun toPresentation(jpg: AnimeCharacters.Data.VoiceActor.Person.Images.Jpg): AnimeCharactersPresentation.Data.VoiceActor.Person.Images.Jpg =
        AnimeCharactersPresentation.Data.VoiceActor.Person.Images.Jpg(jpg.image_url)

    // AnimeVideos

    private fun toPresentation(data: AnimeVideos.Data): AnimeVideosPresentation.Data =
        AnimeVideosPresentation.Data(data.episodes.map { toPresentation(it) }, data.promos.map { toPresentation(it) })

    private fun toPresentation(episode: AnimeVideos.Data.Episode): AnimeVideosPresentation.Data.Episode =
        AnimeVideosPresentation.Data.Episode(episode.episode, toPresentation(episode.images), episode.mal_id, episode.title, episode.url)

    private fun toPresentation(images: AnimeVideos.Data.Episode.Images): AnimeVideosPresentation.Data.Episode.Images =
        AnimeVideosPresentation.Data.Episode.Images(toPresentation(images.jpg))

    private fun toPresentation(jpg: AnimeVideos.Data.Episode.Images.Jpg): AnimeVideosPresentation.Data.Episode.Images.Jpg =
        AnimeVideosPresentation.Data.Episode.Images.Jpg(jpg.image_url)

    private fun toPresentation(promo: AnimeVideos.Data.Promo): AnimeVideosPresentation.Data.Promo =
        AnimeVideosPresentation.Data.Promo(promo.title, toPresentation(promo.trailer))

    private fun toPresentation(trailer: AnimeVideos.Data.Promo.Trailer): AnimeVideosPresentation.Data.Promo.Trailer =
        AnimeVideosPresentation.Data.Promo.Trailer(trailer.embed_url, toPresentation(trailer.images), trailer.url, trailer.youtube_id)

    private fun toPresentation(image: AnimeVideos.Data.Promo.Trailer.ImagesX): AnimeVideosPresentation.Data.Promo.Trailer.ImagesX =
        AnimeVideosPresentation.Data.Promo.Trailer.ImagesX(image.default_image_url, image.large_image_url, image.maximum_image_url, image.medium_image_url, image.small_image_url)
}