package com.lexwilliam.data

import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteSource {

    suspend fun searchAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<SearchRepo>

    suspend fun genreAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<SearchRepo>

    suspend fun topAnime(page: Int, subType: String): Flow<TopRepo>

    suspend fun currentSeasonAnime(): Flow<SeasonRepo>

    suspend fun seasonAnime(year: Int?, season: String?): Flow<SeasonRepo>
}