package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import com.lexwilliam.risuto.model.AnimeListPresentation
import com.lexwilliam.risuto.model.detail.AnimeDetailPresentation
import com.lexwilliam.risuto.model.local.SearchHistoryPresentation
import javax.inject.Inject

interface HistoryMapper {
    fun toDomain(anime: AnimeListPresentation): AnimeHistory
    fun toDomain(search: SearchHistoryPresentation): SearchHistory
    fun toDomain(anime: AnimeDetailPresentation): AnimeHistory
    fun toPresentation(anime: AnimeHistory): AnimeListPresentation
    fun toPresentation(search: SearchHistory): SearchHistoryPresentation
}

class HistoryMapperImpl @Inject constructor(): HistoryMapper {
    override fun toDomain(anime: AnimeListPresentation): AnimeHistory =
        AnimeHistory(
            mal_id = anime.mal_id!!,
            image_url = anime.image_url!!,
            title = anime.title!!,
            synopsis = anime.synopsis!!,
            type = anime.type!!,
            episodes = anime.episodes!!,
            score = anime.score!!,
            members = anime.members!!
        )

    override fun toDomain(search: SearchHistoryPresentation): SearchHistory =
        SearchHistory(search.id, search.query)

    override fun toDomain(anime: AnimeDetailPresentation): AnimeHistory =
        AnimeHistory(
            mal_id = anime.mal_id,
            image_url = anime.image_url,
            title = anime.title,
            synopsis = anime.synopsis,
            type = anime.type,
            episodes = anime.episodes,
            score = anime.score,
            members = anime.members
        )

    override fun toPresentation(anime: AnimeHistory): AnimeListPresentation =
        AnimeListPresentation(
            mal_id = anime.mal_id,
            image_url = anime.image_url,
            title = anime.title,
            synopsis = anime.synopsis,
            type = anime.type,
            episodes = anime.episodes,
            score = anime.score,
            members = anime.members
        )

    override fun toPresentation(search: SearchHistory): SearchHistoryPresentation =
        SearchHistoryPresentation(search.id, search.query)

}