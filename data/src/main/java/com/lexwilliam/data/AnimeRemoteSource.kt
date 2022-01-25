package com.lexwilliam.data

import com.lexwilliam.data.model.detail.AnimeRepo
import com.lexwilliam.data.model.search.SearchRepo
import com.lexwilliam.data.model.season.SeasonRepo
import com.lexwilliam.data.model.top.TopRepo
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteSource {

    suspend fun getSearchAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<SearchRepo>

    suspend fun getTopAnime(page: Int, subType: String): Flow<TopRepo>

    suspend fun getSeasonAnime(year: Int?, season: String?): Flow<SeasonRepo>

    suspend fun getAnimeDetail(id: Int): Flow<AnimeRepo>
}