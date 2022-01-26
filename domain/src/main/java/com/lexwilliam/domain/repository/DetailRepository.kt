package com.lexwilliam.domain.repository

import com.lexwilliam.data.model.remote.detail.Pictures
import com.lexwilliam.domain.model.remote.detail.*
import com.lexwilliam.domain.model.remote.season.SeasonArchive
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun anime(id: Int): Flow<Anime>

    suspend fun characterStaff(id: Int): Flow<CharacterStaff>

    suspend fun seasonArchive(): Flow<SeasonArchive>

    suspend fun episodes(id: Int): Flow<Episodes>

    suspend fun forum(id: Int): Flow<Forum>

    suspend fun moreInfo(id: Int): Flow<MoreInfo>

    suspend fun news(id: Int): Flow<News>

    suspend fun pictures(id: Int): Flow<Pictures>

    suspend fun recommendations(id: Int): Flow<Recommendations>

    suspend fun reviews(id: Int): Flow<Reviews>

    suspend fun stats(id: Int): Flow<Stats>

    suspend fun videos(id: Int): Flow<Videos>
}