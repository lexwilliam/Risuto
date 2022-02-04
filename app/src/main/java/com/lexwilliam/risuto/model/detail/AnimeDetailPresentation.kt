package com.lexwilliam.risuto.model.detail

import com.lexwilliam.risuto.model.common.*

data class AnimeDetailPresentation(
    val aired: AiredPresentation,
    val airing: Boolean,
    val background: Any,
    val broadcast: String,
    val duration: String,
    val ending_themes: List<String>,
    val episodes: Int,
    val favorites: Int,
    val genres: List<GenrePresentation>,
    val image_url: String,
    val licensors: List<LicensorPresentation>,
    val mal_id: Int,
    val members: Int,
    val opening_themes: List<String>,
    val popularity: Int,
    val premiered: String,
    val producers: List<ProducerPresentation>,
    val rank: Int,
    val rating: String,
    val related: RelatedPresentation,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val score: Double,
    val scored_by: Int,
    val source: String,
    val status: String,
    val studios: List<StudioPresentation>,
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
        AiredPresentation("", PropPresentation(FromPresentation(0,0,0), ToPresentation(0,0,0)),"", ""),
        false, "", "", "", listOf(""), 0,
        0, listOf(GenrePresentation(0,"", "", "")), "",
        listOf(LicensorPresentation(0, "", "", "")), 0,0,
        listOf(""), 0, "", listOf(ProducerPresentation(0, "","", "" )),
        0, "", RelatedPresentation(), 0, false, "", 0.0, 0,
        "", "", listOf(StudioPresentation(0, "", "", "")), "",
        "", "", "", listOf(""), "", "", ""
    )
}