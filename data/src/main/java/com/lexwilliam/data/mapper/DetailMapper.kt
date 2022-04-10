package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data.model.remote.anime.AnimeStaffRepo
import com.lexwilliam.data.model.remote.anime.AnimeVideosRepo
import com.lexwilliam.domain.model.remote.anime.AnimeCharacters
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.model.remote.anime.AnimeStaff
import com.lexwilliam.domain.model.remote.anime.AnimeVideos
import javax.inject.Inject

interface DetailMapper {
    fun toDomain(detail: AnimeDetailRepo): AnimeDetail
    fun toDomain(characters: AnimeCharactersRepo): AnimeCharacters
    fun toDomain(videos: AnimeVideosRepo): AnimeVideos
    fun toDomain(staff: AnimeStaffRepo): AnimeStaff
}

class DetailMapperImpl @Inject constructor(): DetailMapper {
    override fun toDomain(detail: AnimeDetailRepo): AnimeDetail =
        AnimeDetail(toDomain(detail.alternative_titles), detail.average_episode_duration, detail.background, toDomain(detail.broadcast), detail.created_at, detail.end_date, detail.genres.map { toDomain(it) }, detail.id, toDomain(detail.main_picture), detail.mean, detail.media_type, toDomain(detail.my_list_status), detail.nsfw, detail.num_episodes, detail.num_list_users, detail.num_scoring_users, detail.pictures.map { toDomain(it) }, detail.popularity, detail.rank, detail.rating, detail.recommendations.map { toDomain(it) }, detail.related_anime.map { toDomain(it) }, detail.related_manga, detail.source, detail.start_date, toDomain(detail.start_season), toDomain(detail.statistics), detail.status, detail.studios.map { toDomain(it) }, detail.synopsis, detail.title, detail.updated_at)

    override fun toDomain(characters: AnimeCharactersRepo): AnimeCharacters =
        AnimeCharacters(characters.data.map { toDomain(it) })

    override fun toDomain(videos: AnimeVideosRepo): AnimeVideos =
        AnimeVideos(toDomain(videos.data))

    override fun toDomain(staff: AnimeStaffRepo): AnimeStaff =
        AnimeStaff(staff.data.map { toDomain(it) })

    private fun toDomain(alter: AnimeDetailRepo.AlternativeTitles): AnimeDetail.AlternativeTitles =
        AnimeDetail.AlternativeTitles(alter.en, alter.ja, alter.synonyms)

    private fun toDomain(broadcast: AnimeDetailRepo.Broadcast): AnimeDetail.Broadcast =
        AnimeDetail.Broadcast(broadcast.day_of_the_week, broadcast.start_time)

    private fun toDomain(genre: AnimeDetailRepo.Genre): AnimeDetail.Genre =
        AnimeDetail.Genre(genre.id, genre.name)

    private fun toDomain(mainPicture: AnimeDetailRepo.MainPicture): AnimeDetail.MainPicture =
        AnimeDetail.MainPicture(mainPicture.large, mainPicture.medium)

    private fun toDomain(status: AnimeDetailRepo.MyListStatus): AnimeDetail.MyListStatus =
        AnimeDetail.MyListStatus(status.is_rewatching, status.num_episodes_watched, status.score, status.status, status.updated_at)

    private fun toDomain(node: AnimeDetailRepo.Node): AnimeDetail.Node =
        AnimeDetail.Node(node.id, toDomain(node.main_picture), node.title)

    private fun toDomain(picture: AnimeDetailRepo.Picture): AnimeDetail.Picture =
        AnimeDetail.Picture(picture.large, picture.medium)

    private fun toDomain(recommendation: AnimeDetailRepo.Recommendation): AnimeDetail.Recommendation =
        AnimeDetail.Recommendation(toDomain(recommendation.node), recommendation.num_recommendations)

    private fun toDomain(relatedAnime: AnimeDetailRepo.RelatedAnime): AnimeDetail.RelatedAnime =
        AnimeDetail.RelatedAnime(toDomain(relatedAnime.node), relatedAnime.relation_type, relatedAnime.relation_type_formatted)

    private fun toDomain(startSeason: AnimeDetailRepo.StartSeason): AnimeDetail.StartSeason =
        AnimeDetail.StartSeason(startSeason.season, startSeason.year)

    private fun toDomain(statistics: AnimeDetailRepo.Statistics): AnimeDetail.Statistics =
        AnimeDetail.Statistics(statistics.num_list_users, toDomain(statistics.status))

    private fun toDomain(status: AnimeDetailRepo.Status): AnimeDetail.Status =
        AnimeDetail.Status(status.completed, status.dropped, status.on_hold, status.plan_to_watch, status.watching)

    private fun toDomain(studio: AnimeDetailRepo.Studio): AnimeDetail.Studio =
        AnimeDetail.Studio(studio.id, studio.name)

    private fun toDomain(data: AnimeCharactersRepo.Data): AnimeCharacters.Data =
        AnimeCharacters.Data(toDomain(data.character), data.role, data.voice_actors.map { toDomain(it) })

    private fun toDomain(character: AnimeCharactersRepo.Data.Character): AnimeCharacters.Data.Character =
        AnimeCharacters.Data.Character(toDomain(character.images), character.mal_id, character.name, character.url)

    private fun toDomain(images: AnimeCharactersRepo.Data.Character.Images): AnimeCharacters.Data.Character.Images =
        AnimeCharacters.Data.Character.Images(toDomain(images.jpg), toDomain(images.webp))

    private fun toDomain(jpg: AnimeCharactersRepo.Data.Character.Images.Jpg): AnimeCharacters.Data.Character.Images.Jpg =
        AnimeCharacters.Data.Character.Images.Jpg(jpg.image_url, jpg.small_image_url)

    private fun toDomain(webp: AnimeCharactersRepo.Data.Character.Images.Webp): AnimeCharacters.Data.Character.Images.Webp =
        AnimeCharacters.Data.Character.Images.Webp(webp.image_url, webp.small_image_url)

    private fun toDomain(va: AnimeCharactersRepo.Data.VoiceActor): AnimeCharacters.Data.VoiceActor =
        AnimeCharacters.Data.VoiceActor(va.language, toDomain(va.person))

    private fun toDomain(person: AnimeCharactersRepo.Data.VoiceActor.Person): AnimeCharacters.Data.VoiceActor.Person =
        AnimeCharacters.Data.VoiceActor.Person(toDomain(person.images), person.mal_id, person.name, person.url)

    private fun toDomain(images: AnimeCharactersRepo.Data.VoiceActor.Person.Images): AnimeCharacters.Data.VoiceActor.Person.Images =
        AnimeCharacters.Data.VoiceActor.Person.Images(toDomain(images.jpg))

    private fun toDomain(jpg: AnimeCharactersRepo.Data.VoiceActor.Person.Images.Jpg): AnimeCharacters.Data.VoiceActor.Person.Images.Jpg =
        AnimeCharacters.Data.VoiceActor.Person.Images.Jpg(jpg.image_url)

    // AnimeVideos

    private fun toDomain(data: AnimeVideosRepo.Data): AnimeVideos.Data =
        AnimeVideos.Data(data.episodes.map { toDomain(it) }, data.promos.map { toDomain(it) })

    private fun toDomain(episode: AnimeVideosRepo.Data.Episode): AnimeVideos.Data.Episode =
        AnimeVideos.Data.Episode(episode.episode, toDomain(episode.images), episode.mal_id, episode.title, episode.url)

    private fun toDomain(images: AnimeVideosRepo.Data.Episode.Images): AnimeVideos.Data.Episode.Images =
        AnimeVideos.Data.Episode.Images(toDomain(images.jpg))

    private fun toDomain(jpg: AnimeVideosRepo.Data.Episode.Images.Jpg): AnimeVideos.Data.Episode.Images.Jpg =
        AnimeVideos.Data.Episode.Images.Jpg(jpg.image_url)

    private fun toDomain(promo: AnimeVideosRepo.Data.Promo): AnimeVideos.Data.Promo =
        AnimeVideos.Data.Promo(promo.title, toDomain(promo.trailer))

    private fun toDomain(trailer: AnimeVideosRepo.Data.Promo.Trailer): AnimeVideos.Data.Promo.Trailer =
        AnimeVideos.Data.Promo.Trailer(trailer.embed_url, toDomain(trailer.images), trailer.url, trailer.youtube_id)

    private fun toDomain(image: AnimeVideosRepo.Data.Promo.Trailer.ImagesX): AnimeVideos.Data.Promo.Trailer.ImagesX =
        AnimeVideos.Data.Promo.Trailer.ImagesX(image.default_image_url, image.large_image_url, image.maximum_image_url, image.medium_image_url, image.small_image_url)

    // AnimeStaff

    private fun toDomain(data: AnimeStaffRepo.Data): AnimeStaff.Data =
        AnimeStaff.Data(toDomain(data.person), data.positions)

    private fun toDomain(person: AnimeStaffRepo.Data.Person): AnimeStaff.Data.Person =
        AnimeStaff.Data.Person(toDomain(person.images), person.mal_id, person.name, person.url)

    private fun toDomain(images: AnimeStaffRepo.Data.Person.Images): AnimeStaff.Data.Person.Images =
        AnimeStaff.Data.Person.Images(toDomain(images.jpg))

    private fun toDomain(jpg: AnimeStaffRepo.Data.Person.Images.Jpg): AnimeStaff.Data.Person.Images.Jpg =
        AnimeStaff.Data.Person.Images.Jpg(jpg.image_url)
}