package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.local.AnimeHistoryRepo
import com.lexwilliam.data.model.local.SearchHistoryRepo
import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import javax.inject.Inject

interface HistoryMapper {
    fun toRepo(anime: AnimeHistory): AnimeHistoryRepo
    fun toRepo(search: SearchHistory): SearchHistoryRepo
    fun toDomain(anime: AnimeHistoryRepo): AnimeHistory
    fun toDomain(search: SearchHistoryRepo): SearchHistory
}

class HistoryMapperImpl @Inject constructor(): HistoryMapper {
    override fun toRepo(anime: AnimeHistory): AnimeHistoryRepo =
        AnimeHistoryRepo(anime.mal_id, anime.image_url, anime.title, anime.timeAdded)

    override fun toRepo(search: SearchHistory): SearchHistoryRepo =
        SearchHistoryRepo(search.id, search.query)

    override fun toDomain(anime: AnimeHistoryRepo): AnimeHistory =
        AnimeHistory(anime.mal_id, anime.image_url, anime.title, anime.timeAdded)

    override fun toDomain(search: SearchHistoryRepo): SearchHistory =
        SearchHistory(search.id, search.query)

}