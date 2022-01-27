package com.lexwilliam.data.model.remote.detail

import com.lexwilliam.data.model.common.*


data class AnimeDetailRepo(
    val aired: AiredRepo?,
    val airing: Boolean?,
    val background: Any?,
    val broadcast: String?,
    val duration: String?,
    val ending_themes: List<String>?,
    val episodes: Int?,
    val favorites: Int?,
    val genres: List<GenreRepo>?,
    val image_url: String?,
    val licensors: List<LicensorRepo>?,
    val mal_id: Int?,
    val members: Int?,
    val opening_themes: List<String>?,
    val popularity: Int?,
    val premiered: String?,
    val producers: List<ProducerRepo>?,
    val rank: Int?,
    val rating: String?,
    val related: RelatedRepo?,
    val request_cache_expiry: Int?,
    val request_cached: Boolean?,
    val request_hash: String?,
    val score: Double?,
    val scored_by: Int?,
    val source: String?,
    val status: String?,
    val studios: List<StudioRepo>?,
    val synopsis: String?,
    val title: String?,
    val title_english: Any?,
    val title_japanese: String?,
    val title_synonyms: List<Any>?,
    val trailer_url: String?,
    val type: String?,
    val url: String?
)