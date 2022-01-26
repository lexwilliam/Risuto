package com.lexwilliam.data.repository

import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.remote.JikanService
import com.lexwilliam.domain.model.SeasonArchive
import com.example.risuto.domain.model.detail.*
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.model.detail.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val jikanService: JikanService
): DetailRepository {

    override suspend fun seasonArchive(): Flow<SeasonArchive> = flow {
        val seasonArchive = jikanService.getSeasonArchiveResult()
        val item = seasonArchive.toDomain()
        emit(item)
    }

    override suspend fun anime(id: Int): Flow<Anime> = flow {
        val animeResponse = jikanService.getAnimeResult(id)
        val item = animeResponse.toDomain()
        emit(item)

    }

    override suspend fun characterStaff(id: Int): Flow<CharacterStaff> = flow {
        val characterStaffResponse = jikanService.getCharacterStaffResult(id)
        val item = characterStaffResponse.toDomain()
        emit(item)
    }

    override suspend fun episodes(id: Int): Flow<Episodes> = flow {
        val episodes = jikanService.getEpisodesResult(id)
        val item = episodes.toDomain()
        emit(item)
    }

    override suspend fun forum(id: Int): Flow<Forum> = flow {
        val forum = jikanService.getForumResult(id)
        val item = forum.toDomain()
        emit(item)
    }

    override suspend fun moreInfo(id: Int): Flow<MoreInfo> = flow {
        val moreInfo = jikanService.getMoreInfoResult(id)
        val item = moreInfo.toDomain()
        emit(item)
    }

    override suspend fun news(id: Int): Flow<News> = flow {
        val news = jikanService.getNewsResult(id)
        val item = news.toDomain()
        emit(item)
    }

    override suspend fun pictures(id: Int): Flow<Pictures> = flow {
        val pictures = jikanService.getPicturesResult(id)
        val item = pictures.toDomain()
        emit(item)
    }

    override suspend fun recommendations(id: Int): Flow<Recommendations> = flow {
        val recommendations = jikanService.getRecommendationsResult(id)
        val item = recommendations.toDomain()
        emit(item)
    }

    override suspend fun reviews(id: Int): Flow<Reviews> = flow {
        val reviews = jikanService.getReviewsResult(id)
        val item = reviews.toDomain()
        emit(item)
    }

    override suspend fun stats(id: Int): Flow<Stats> = flow {
        val stats = jikanService.getStatsResult(id)
        val item = stats.toDomain()
        emit(item)
    }

    override suspend fun videos(id: Int): Flow<Videos> = flow {
        val videos = jikanService.getVideosResult(id)
        val item = videos.toDomain()
        emit(item)
    }

}