package com.lexwilliam.data_remote.model.anime

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class AnimeDetailResponse(
    val alternative_titles: AlternativeTitles?,
    val average_episode_duration: Int?,
    val background: String?,
    val broadcast: Broadcast?,
    val created_at: String?,
    val end_date: String?,
    val genres: List<Genre>?,
    val id: Int,
    val main_picture: MainPicture?,
    val mean: Double?,
    val media_type: String,
    val my_list_status: MyListStatus?,
    val nsfw: String?,
    val num_episodes: Int,
    val num_list_users: Int,
    val num_scoring_users: Int,
    val pictures: List<Picture>,
    val popularity: Int,
    val rank: Int?,
    val rating: String?,
    val recommendations: List<Recommendation>,
    val related_anime: List<RelatedAnime>,
    val related_manga: List<Any>,
    val source: String?,
    val start_date: String?,
    val start_season: StartSeason?,
    val statistics: Statistics?,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String?,
    val title: String,
    val updated_at: String
) {
    data class AlternativeTitles(
        val en: String,
        val ja: String,
        val synonyms: List<String>
    )
    data class Broadcast(
        val day_of_the_week: String,
        val start_time: String
    )
    data class Genre(
        val id: Int,
        val name: String
    )
    data class MainPicture(
        val large: String,
        val medium: String
    )
    data class MyListStatus(
        val is_rewatching: Boolean,
        val num_episodes_watched: Int,
        val score: Int,
        val status: String,
        val updated_at: String
    )
    data class Node(
        val id: Int,
        val main_picture: MainPicture,
        val title: String
    )
    data class Picture(
        val large: String,
        val medium: String
    )
    data class Recommendation(
        val node: Node,
        val num_recommendations: Int
    )
    data class RelatedAnime(
        val node: Node,
        val relation_type: String,
        val relation_type_formatted: String
    )
    data class StartSeason(
        val season: String,
        val year: Int
    )
    data class Statistics(
        val num_list_users: Int,
        val status: Status
    )
    data class Status(
        val completed: String,
        val dropped: String,
        val on_hold: String,
        val plan_to_watch: String,
        val watching: String
    )
    data class Studio(
        val id: Int,
        val name: String
    )
}