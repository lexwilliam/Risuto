package com.lexwilliam.risutov2.model.detail

import com.example.risuto.data.remote.model.detail.*

data class AnimePresentation(
    val aired: Aired,
    val airing: Boolean,
    val background: Any,
    val broadcast: String,
    val duration: String,
    val ending_themes: List<String>,
    val episodes: Int,
    val favorites: Int,
    val genres: List<Genre>,
    val image_url: String,
    val licensors: List<Licensor>,
    val mal_id: Int,
    val members: Int,
    val opening_themes: List<String>,
    val popularity: Int,
    val premiered: String,
    val producers: List<Producer>,
    val rank: Int,
    val rating: String,
    val related: Related,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val score: Double,
    val scored_by: Int,
    val source: String,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String,
    val title: String,
    val title_english: Any,
    val title_japanese: String,
    val title_synonyms: List<Any>,
    val trailer_url: String,
    val type: String,
    val url: String
) {
    constructor() : this(
        Aired("", Prop(From(0,0,0), To(0,0,0)),"", ""),
        false, "", "", "", listOf(""), 0,
        0, listOf(Genre(0,"", "", "")), "",
        listOf(Licensor(0, "", "", "")), 0,0,
        listOf(""), 0, "", listOf(Producer(0, "","", "" )),
        0, "", Related(), 0, false, "", 0.0, 0,
        "", "", listOf(Studio(0, "", "", "")), "",
        "", "", "", listOf(""), "", "", ""
    )
}