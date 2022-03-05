package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data_remote.model.anime.AnimeDetailResponse
import javax.inject.Inject

interface DetailMapper {
    fun toRepo(detail: AnimeDetailResponse): AnimeDetailRepo
}

class DetailMapperImpl @Inject constructor(): DetailMapper {
    override fun toRepo(detail: AnimeDetailResponse): AnimeDetailRepo =
        AnimeDetailRepo(toRepo(detail.alternative_titles?:AnimeDetailResponse.AlternativeTitles("", "", emptyList())), detail.average_episode_duration?:-1, detail.background?:"", toRepo(detail.broadcast?:AnimeDetailResponse.Broadcast("", "")), detail.created_at?:"", detail.end_date?:"", detail.genres?.map { toRepo(it) }?: emptyList(), detail.id, toRepo(detail.main_picture?:AnimeDetailResponse.MainPicture("", "")), detail.mean?:-1.0, detail.media_type, toRepo(detail.my_list_status?:AnimeDetailResponse.MyListStatus(false, -1, -1, "", "")), detail.nsfw?:"", detail.num_episodes, detail.num_list_users, detail.num_scoring_users, detail.pictures.map { toRepo(it) }, detail.popularity, detail.rank?:-1, detail.rating?:"", detail.recommendations.map { toRepo(it) }, detail.related_anime.map { toRepo(it) }, detail.related_manga, detail.source?:"", detail.start_date?:"", toRepo(detail.start_season?:AnimeDetailResponse.StartSeason("", -1)), toRepo(detail.statistics?: AnimeDetailResponse.Statistics(-1, AnimeDetailResponse.Status("","","","",""))), detail.status, detail.studios.map { toRepo(it) }, detail.synopsis?:"", detail.title, detail.updated_at)

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
}