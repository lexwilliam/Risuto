package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data.model.remote.anime.AnimeVideosRepo
import com.lexwilliam.data_remote.model.anime.AnimeCharactersResponse
import com.lexwilliam.data_remote.model.anime.AnimeDetailResponse
import com.lexwilliam.data_remote.model.anime.AnimeVideosResponse
import javax.inject.Inject

interface DetailMapper {
    fun toRepo(detail: AnimeDetailResponse): AnimeDetailRepo
    fun toRepo(characters: AnimeCharactersResponse): AnimeCharactersRepo
    fun toRepo(videos: AnimeVideosResponse): AnimeVideosRepo
}

class DetailMapperImpl @Inject constructor(): DetailMapper {
    override fun toRepo(detail: AnimeDetailResponse): AnimeDetailRepo =
        AnimeDetailRepo(toRepo(detail.alternative_titles?:AnimeDetailResponse.AlternativeTitles("", "", emptyList())), detail.average_episode_duration?:-1, detail.background?:"", toRepo(detail.broadcast?:AnimeDetailResponse.Broadcast("", "")), detail.created_at?:"", detail.end_date?:"", detail.genres?.map { toRepo(it) }?: emptyList(), detail.id, toRepo(detail.main_picture?:AnimeDetailResponse.MainPicture("", "")), detail.mean?:-1.0, detail.media_type, toRepo(detail.my_list_status?:AnimeDetailResponse.MyListStatus(false, -1, -1, "", "")), detail.nsfw?:"", detail.num_episodes, detail.num_list_users, detail.num_scoring_users, detail.pictures.map { toRepo(it) }, detail.popularity, detail.rank?:-1, detail.rating?:"", detail.recommendations.map { toRepo(it) }, detail.related_anime.map { toRepo(it) }, detail.related_manga, detail.source?:"", detail.start_date?:"", toRepo(detail.start_season?:AnimeDetailResponse.StartSeason("", -1)), toRepo(detail.statistics?: AnimeDetailResponse.Statistics(-1, AnimeDetailResponse.Status("","","","",""))), detail.status, detail.studios.map { toRepo(it) }, detail.synopsis?:"", detail.title, detail.updated_at)

    override fun toRepo(characters: AnimeCharactersResponse): AnimeCharactersRepo =
        AnimeCharactersRepo(characters.data.map { toRepo(it) })

    override fun toRepo(videos: AnimeVideosResponse): AnimeVideosRepo =
        AnimeVideosRepo(toRepo(videos.data))

    // AnimeDetail

    private fun toRepo(alter: AnimeDetailResponse.AlternativeTitles): AnimeDetailRepo.AlternativeTitles =
        AnimeDetailRepo.AlternativeTitles(alter.en, alter.ja, alter.synonyms)

    private fun toRepo(broadcast: AnimeDetailResponse.Broadcast): AnimeDetailRepo.Broadcast =
        AnimeDetailRepo.Broadcast(broadcast.day_of_the_week, broadcast.start_time)

    private fun toRepo(genre: AnimeDetailResponse.Genre): AnimeDetailRepo.Genre =
        AnimeDetailRepo.Genre(genre.id, genre.name)

    private fun toRepo(mainPicture: AnimeDetailResponse.MainPicture): AnimeDetailRepo.MainPicture =
        AnimeDetailRepo.MainPicture(mainPicture.large, mainPicture.medium)

    private fun toRepo(status: AnimeDetailResponse.MyListStatus): AnimeDetailRepo.MyListStatus =
        AnimeDetailRepo.MyListStatus(status.is_rewatching, status.num_episodes_watched, status.score, status.status, status.updated_at)

    private fun toRepo(node: AnimeDetailResponse.Node): AnimeDetailRepo.Node =
        AnimeDetailRepo.Node(node.id, toRepo(node.main_picture), node.title)

    private fun toRepo(picture: AnimeDetailResponse.Picture): AnimeDetailRepo.Picture =
        AnimeDetailRepo.Picture(picture.large, picture.medium)

    private fun toRepo(recommendation: AnimeDetailResponse.Recommendation): AnimeDetailRepo.Recommendation =
        AnimeDetailRepo.Recommendation(toRepo(recommendation.node), recommendation.num_recommendations)

    private fun toRepo(relatedAnime: AnimeDetailResponse.RelatedAnime): AnimeDetailRepo.RelatedAnime =
        AnimeDetailRepo.RelatedAnime(toRepo(relatedAnime.node), relatedAnime.relation_type, relatedAnime.relation_type_formatted)

    private fun toRepo(startSeason: AnimeDetailResponse.StartSeason): AnimeDetailRepo.StartSeason =
        AnimeDetailRepo.StartSeason(startSeason.season, startSeason.year)

    private fun toRepo(statistics: AnimeDetailResponse.Statistics): AnimeDetailRepo.Statistics =
        AnimeDetailRepo.Statistics(statistics.num_list_users, toRepo(statistics.status))

    private fun toRepo(status: AnimeDetailResponse.Status): AnimeDetailRepo.Status =
        AnimeDetailRepo.Status(status.completed, status.dropped, status.on_hold, status.plan_to_watch, status.watching)

    private fun toRepo(studio: AnimeDetailResponse.Studio): AnimeDetailRepo.Studio =
        AnimeDetailRepo.Studio(studio.id, studio.name)

    // AnimeCharacters

    private fun toRepo(data: AnimeCharactersResponse.Data): AnimeCharactersRepo.Data =
        AnimeCharactersRepo.Data(toRepo(data.character), data.role, data.voice_actors.map { toRepo(it) })

    private fun toRepo(character: AnimeCharactersResponse.Data.Character): AnimeCharactersRepo.Data.Character =
        AnimeCharactersRepo.Data.Character(toRepo(character.images), character.mal_id, character.name, character.url)

    private fun toRepo(images: AnimeCharactersResponse.Data.Character.Images): AnimeCharactersRepo.Data.Character.Images =
        AnimeCharactersRepo.Data.Character.Images(toRepo(images.jpg), toRepo(images.webp))

    private fun toRepo(jpg: AnimeCharactersResponse.Data.Character.Images.Jpg): AnimeCharactersRepo.Data.Character.Images.Jpg =
        AnimeCharactersRepo.Data.Character.Images.Jpg(jpg.image_url?:"", jpg.small_image_url?:"")

    private fun toRepo(webp: AnimeCharactersResponse.Data.Character.Images.Webp): AnimeCharactersRepo.Data.Character.Images.Webp =
        AnimeCharactersRepo.Data.Character.Images.Webp(webp.image_url?:"", webp.small_image_url?:"")

    private fun toRepo(va: AnimeCharactersResponse.Data.VoiceActor): AnimeCharactersRepo.Data.VoiceActor =
        AnimeCharactersRepo.Data.VoiceActor(va.language, toRepo(va.person))

    private fun toRepo(person: AnimeCharactersResponse.Data.VoiceActor.Person): AnimeCharactersRepo.Data.VoiceActor.Person =
        AnimeCharactersRepo.Data.VoiceActor.Person(toRepo(person.images), person.mal_id, person.name, person.url)

    private fun toRepo(images: AnimeCharactersResponse.Data.VoiceActor.Person.Images): AnimeCharactersRepo.Data.VoiceActor.Person.Images =
        AnimeCharactersRepo.Data.VoiceActor.Person.Images(toRepo(images.jpg))

    private fun toRepo(jpg: AnimeCharactersResponse.Data.VoiceActor.Person.Images.Jpg): AnimeCharactersRepo.Data.VoiceActor.Person.Images.Jpg =
        AnimeCharactersRepo.Data.VoiceActor.Person.Images.Jpg(jpg.image_url?:"")

    // AnimeVideos

    private fun toRepo(data: AnimeVideosResponse.Data): AnimeVideosRepo.Data =
        AnimeVideosRepo.Data(data.episodes?.map { toRepo(it) } ?: emptyList(), data.promo?.map { toRepo(it) } ?: emptyList())

    private fun toRepo(episode: AnimeVideosResponse.Data.Episode): AnimeVideosRepo.Data.Episode =
        AnimeVideosRepo.Data.Episode(episode.episode, toRepo(episode.images), episode.mal_id, episode.title, episode.url)

    private fun toRepo(images: AnimeVideosResponse.Data.Episode.Images): AnimeVideosRepo.Data.Episode.Images =
        AnimeVideosRepo.Data.Episode.Images(toRepo(images.jpg))

    private fun toRepo(jpg: AnimeVideosResponse.Data.Episode.Images.Jpg): AnimeVideosRepo.Data.Episode.Images.Jpg =
        AnimeVideosRepo.Data.Episode.Images.Jpg(jpg.image_url?:"")

    private fun toRepo(promo: AnimeVideosResponse.Data.Promo): AnimeVideosRepo.Data.Promo =
        AnimeVideosRepo.Data.Promo(promo.title, toRepo(promo.trailer))

    private fun toRepo(trailer: AnimeVideosResponse.Data.Promo.Trailer): AnimeVideosRepo.Data.Promo.Trailer =
        AnimeVideosRepo.Data.Promo.Trailer(trailer.embed_url, toRepo(trailer.images), trailer.url, trailer.youtube_id)

    private fun toRepo(image: AnimeVideosResponse.Data.Promo.Trailer.Images): AnimeVideosRepo.Data.Promo.Trailer.ImagesX =
        AnimeVideosRepo.Data.Promo.Trailer.ImagesX(image.image_url, image.large_image_url, image.maximum_image_url, image.medium_image_url, image.small_image_url)
}