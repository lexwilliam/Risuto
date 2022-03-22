package com.lexwilliam.data_local.mapper

import com.lexwilliam.data.model.local.AnimeHistoryRepo
import com.lexwilliam.data.model.local.SearchHistoryRepo
import com.lexwilliam.data_local.model.AnimeHistoryEntity
import com.lexwilliam.data_local.model.SearchHistoryEntity
import javax.inject.Inject

interface HistoryMapper {
    fun toRepo(anime: AnimeHistoryEntity): AnimeHistoryRepo
    fun toRepo(search: SearchHistoryEntity): SearchHistoryRepo
    fun toEntity(anime: AnimeHistoryRepo): AnimeHistoryEntity
    fun toEntity(search: SearchHistoryRepo): SearchHistoryEntity
}

class HistoryMapperImpl @Inject constructor(): HistoryMapper {
    override fun toRepo(anime: AnimeHistoryEntity): AnimeHistoryRepo =
        AnimeHistoryRepo(anime.mal_id, anime.image_url, anime.title, anime.timeAdded)

    override fun toRepo(search: SearchHistoryEntity): SearchHistoryRepo =
        SearchHistoryRepo(search.query)

    override fun toEntity(anime: AnimeHistoryRepo): AnimeHistoryEntity =
        AnimeHistoryEntity(anime.mal_id, anime.image_url, anime.title, anime.timeAdded)

    override fun toEntity(search: SearchHistoryRepo): SearchHistoryEntity =
        SearchHistoryEntity(search.query)

}