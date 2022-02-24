package com.lexwilliam.data

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.search.SearchAnimeRepo
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteSource {

    suspend fun searchAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<SearchRepo>

    fun genreAnime(q: String?, type: String?, status: String?, genre: Int?, orderBy: String?, sort: String?): Flow<PagingData<SearchAnimeRepo>>

    suspend fun topAnime(page: Int, subType: String): Flow<TopRepo>

    suspend fun currentSeasonAnime(): Flow<SeasonRepo>

    suspend fun seasonAnime(year: Int?, season: String?): Flow<SeasonRepo>

    suspend fun getAnimeById(id: Int): Flow<AnimeRepo.Data>

    suspend fun getTopAnime(): Flow<AnimeRepo>

    suspend fun getSeasonNow(): Flow<AnimeRepo>

    suspend fun getSeason(year: Int, season: String): Flow<AnimeRepo>

    suspend fun getSchedules(dayOfWeek: String): Flow<AnimeRepo>

    suspend fun getSearchAnime(
        page: Int,
        limit: Int,
        q: String,
        type: String,
        score: Double,
        minScore: Double,
        maxScore: Double,
        status: String,
        rating: String,
        sfw: Boolean,
        genres: String,
        genresExclude: String,
        orderBy: String,
        sort: String,
        letter: String,
        producer: String
    ): Flow<AnimeRepo>
}