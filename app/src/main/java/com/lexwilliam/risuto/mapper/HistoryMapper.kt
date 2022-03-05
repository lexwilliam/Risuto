package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.SearchHistoryPresentation
import com.lexwilliam.risuto.model.ShortAnimePresentation
import javax.inject.Inject

interface HistoryMapper {
    fun toDomain(search: SearchHistoryPresentation): SearchHistory
    fun toDomain(anime: AnimeDetailPresentation): AnimeHistory
    fun toPresentation(anime: AnimeHistory): ShortAnimePresentation
    fun toPresentation(search: SearchHistory): SearchHistoryPresentation
}

class HistoryMapperImpl @Inject constructor(): HistoryMapper {

    override fun toDomain(search: SearchHistoryPresentation): SearchHistory =
        SearchHistory(search.id, search.query)

    override fun toDomain(anime: AnimeDetailPresentation): AnimeHistory =
        AnimeHistory(
            mal_id = anime.id,
            image_url = anime.main_picture.medium,
            title = anime.title
        )

    override fun toPresentation(anime: AnimeHistory): ShortAnimePresentation =
        ShortAnimePresentation(
            mal_id = anime.mal_id,
            image_url = anime.image_url,
            title = anime.title
        )

    override fun toPresentation(search: SearchHistory): SearchHistoryPresentation =
        SearchHistoryPresentation(search.id, search.query)

}