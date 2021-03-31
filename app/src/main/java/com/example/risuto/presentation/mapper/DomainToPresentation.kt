package com.chun2maru.risutomvvm.presentation.mapper

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.data.remote.model.*
import com.example.risuto.domain.model.Anime
import com.example.risuto.domain.model.TopAnime
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.model.RowStylePresentation

internal fun Anime.toPresentation(): AnimePresentation {
    return AnimePresentation(
        aired?: Aired("", Prop(From(0,0,0), To(0,0,0)),"", ""),
        airing?: false,
        background?: "",
        broadcast?: "",
        duration?: "",
        ending_themes?:listOf(""),
        episodes?:0,
        favorites?:0,
        genres?:listOf(Genre(0,"", "", "")),
        image_url?:"",
        licensors?:listOf(Licensor(0, "", "", "")),
        mal_id?:0,
        members?:0,
        opening_themes?:listOf(""),
        popularity?:0,
        premiered?:"",
        producers?:listOf(Producer(0, "","", "" )),
        rank?:0,
        rating?:"",
        related?:Related(),
        request_cache_expiry?:0,
        request_cached?:false,
        request_hash?:"",
        score?:0.0,
        scored_by?:0,
        source?:"",
        status?:"",
        studios?:listOf(Studio(0, "", "", "")),
        synopsis?:"",
        title?:"",
        title_english?:"",
        title_japanese?:"",
        title_synonyms?:listOf(""),
        trailer_url?:"",
        type?:"",
        url?:"")
}

internal fun SearchAnime.toRow(): RowStylePresentation {
    return RowStylePresentation(mal_id, image_url?: "", title, synopsis?: "", type, episodes?: 0, score, members)
}

internal fun TopAnime.toGrid(): GridStylePresentation {
    return GridStylePresentation(mal_id, image_url?: "", title, type, episodes?:0, score, members)
}