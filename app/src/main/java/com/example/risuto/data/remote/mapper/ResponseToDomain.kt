package com.chun2maru.risutomvvm.data.mapper

import com.example.risuto.data.remote.model.list.SearchAnimeResponse
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.data.remote.model.detail.*
import com.example.risuto.data.remote.model.list.SeasonAnimeResponse
import com.example.risuto.data.remote.model.list.TopAnimeResponse
import com.example.risuto.data.remote.model.detail.SeasonArchiveResponse
import com.example.risuto.domain.model.*

internal fun SearchAnimeResponse.toDomain(): SearchAnime {
    return SearchAnime(mal_id, url, image_url?: "", title, airing, synopsis?: "", type, episodes?: 0, score, start_date?: "", end_date?: "", members, rated)
}

internal fun TopAnimeResponse.toDomain(): TopAnime {
    return TopAnime(mal_id, rank, title, url, image_url?: "", type, episodes?: 0, start_date?: "", end_date?: "", members, score)
}

internal fun SeasonAnimeResponse.toDomain(): SeasonAnime {
    return SeasonAnime(airing_start?: "", continuing, episodes?: 0, genres, image_url?: "", kids, licensors, mal_id, members, producers, r18, score?: 0.0, source, synopsis, title, type, url)
}

internal fun SeasonArchiveResponse.toDomain(): SeasonArchive {
    return SeasonArchive(request_hash, request_cached, request_cache_expiry, archive)
}

internal fun AnimeResponse.toDomain(): Anime {
    return Anime(
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
        related?: Related(),
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

internal fun CharacterStaffResponse.toDomain(): CharacterStaff {
    return CharacterStaff(characters?: listOf(), request_cache_expiry, request_cached, request_hash, staff?: listOf())
}