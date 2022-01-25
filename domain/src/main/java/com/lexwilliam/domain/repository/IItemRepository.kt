package com.example.risuto.domain.repository

import com.lexwilliam.domain.model.SeasonArchive
import com.lexwilliam.domain.model.detail.*
import kotlinx.coroutines.flow.Flow

interface IItemRepository {

    suspend fun getAnime(id: Int): Flow<Anime>

    suspend fun getCharacterStaff(id: Int): Flow<CharacterStaff>

    suspend fun getSeasonArchive(): Flow<SeasonArchive>

    suspend fun getEpisodes(id: Int): Flow<Episodes>

    suspend fun getForum(id: Int): Flow<Forum>

    suspend fun getMoreInfo(id: Int): Flow<MoreInfo>

    suspend fun getNews(id: Int): Flow<News>

    suspend fun getPictures(id: Int): Flow<Pictures>

    suspend fun getRecommendations(id: Int): Flow<Recommendations>

    suspend fun getReviews(id: Int): Flow<Reviews>

    suspend fun getStats(id: Int): Flow<Stats>

    suspend fun getVideos(id: Int): Flow<Videos>
}