package com.lexwilliam.risuto.mapper

import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import javax.inject.Inject

interface DetailMapper {
    fun toPresentation(detail: AnimeDetail): AnimeDetailPresentation
}

class DetailMapperImpl @Inject constructor(): DetailMapper {
    override fun toPresentation(detail: AnimeDetail): AnimeDetailPresentation =
        AnimeDetailPresentation(toPresentation(detail.alternative_titles), detail.average_episode_duration, detail.background, toPresentation(detail.broadcast), detail.created_at, detail.end_date, detail.genres.map { toPresentation(it) }, detail.id, toPresentation(detail.main_picture), detail.mean, detail.media_type, toPresentation(detail.my_list_status), detail.nsfw, detail.num_episodes, detail.num_list_users, detail.num_scoring_users, detail.pictures.map { toPresentation(it) }, detail.popularity, detail.rank, detail.rating, detail.recommendations.map { toPresentation(it) }, detail.related_anime.map { toPresentation(it) }, detail.related_manga, detail.source, detail.start_date, toPresentation(detail.start_season), toPresentation(detail.statistics), detail.status, detail.studios.map { toPresentation(it) }, detail.synopsis, detail.title, detail.updated_at)

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
}