package com.example.risuto.data.remote.model

data class AnimeResponse(
    val aired: Aired?,
    val airing: Boolean?,
    val background: Any?,
    val broadcast: String?,
    val duration: String?,
    val ending_themes: List<String>?,
    val episodes: Int?,
    val favorites: Int?,
    val genres: List<Genre>?,
    val image_url: String?,
    val licensors: List<Licensor>?,
    val mal_id: Int?,
    val members: Int?,
    val opening_themes: List<String>?,
    val popularity: Int?,
    val premiered: String?,
    val producers: List<Producer>?,
    val rank: Int?,
    val rating: String?,
    val related: Related?,
    val request_cache_expiry: Int?,
    val request_cached: Boolean?,
    val request_hash: String?,
    val score: Double?,
    val scored_by: Int?,
    val source: String?,
    val status: String?,
    val studios: List<Studio>?,
    val synopsis: String?,
    val title: String?,
    val title_english: Any?,
    val title_japanese: String?,
    val title_synonyms: List<Any>?,
    val trailer_url: String?,
    val type: String?,
    val url: String?
)

data class Aired(
    val from: String?,
    val prop: Prop?,
    val string: String?,
    val to: Any?
)

data class Genre(
    val mal_id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)

data class Licensor(
    val mal_id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)

data class Producer(
    val mal_id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)

class Related(
)

data class Studio(
    val mal_id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)

data class Prop(
    val from: From?,
    val to: To?
)

data class From(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

data class To(
    val day: Any?,
    val month: Any?,
    val year: Any?
)