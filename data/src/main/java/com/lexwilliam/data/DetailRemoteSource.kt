package com.lexwilliam.data

import com.lexwilliam.data.model.remote.detail.*
import com.lexwilliam.data.model.remote.season.SeasonArchiveRepo
import kotlinx.coroutines.flow.Flow

interface DetailRemoteSource {

    suspend fun anime(id: Int): Flow<AnimeRepo>

    suspend fun characterStaff(id: Int): Flow<CharacterStaffRepo>

    suspend fun episodes(id: Int): Flow<EpisodesRepo>

    suspend fun forum(id: Int): Flow<ForumRepo>

    suspend fun moreInfo(id: Int): Flow<MoreInfoRepo>

    suspend fun news(id: Int): Flow<NewsRepo>

    suspend fun pictures(id: Int): Flow<PicturesRepo>

    suspend fun recommendations(id: Int): Flow<RecommendationsRepo>

    suspend fun reviews(id: Int): Flow<ReviewsRepo>

    suspend fun stats(id: Int): Flow<StatsRepo>

    suspend fun videos(id: Int): Flow<VideosRepo>

}