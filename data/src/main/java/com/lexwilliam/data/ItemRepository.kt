package com.lexwilliam.data

import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.remote.JikanService
import com.lexwilliam.domain.model.SeasonArchive
import com.example.risuto.domain.model.detail.*
import com.example.risuto.domain.repository.IItemRepository
import com.lexwilliam.domain.model.detail.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val jikanService: JikanService
): IItemRepository {

    override suspend fun getSeasonArchive(): Flow<SeasonArchive> = flow {
        val seasonArchive = jikanService.getSeasonArchiveResult()
        val item = seasonArchive.toDomain()
        emit(item)
    }

    override suspend fun getAnime(id: Int): Flow<Anime> = flow {
        val animeResponse = jikanService.getAnimeResult(id)
        val item = animeResponse.toDomain()
        emit(item)

    }

    override suspend fun getCharacterStaff(id: Int): Flow<CharacterStaff> = flow {
        val characterStaffResponse = jikanService.getCharacterStaffResult(id)
        val item = characterStaffResponse.toDomain()
        emit(item)
    }

    override suspend fun getEpisodes(id: Int): Flow<Episodes> = flow {
        val episodes = jikanService.getEpisodesResult(id)
        val item = episodes.toDomain()
        emit(item)
    }

    override suspend fun getForum(id: Int): Flow<Forum> = flow {
        val forum = jikanService.getForumResult(id)
        val item = forum.toDomain()
        emit(item)
    }

    override suspend fun getMoreInfo(id: Int): Flow<MoreInfo> = flow {
        val moreInfo = jikanService.getMoreInfoResult(id)
        val item = moreInfo.toDomain()
        emit(item)
    }

    override suspend fun getNews(id: Int): Flow<News> = flow {
        val news = jikanService.getNewsResult(id)
        val item = news.toDomain()
        emit(item)
    }

    override suspend fun getPictures(id: Int): Flow<Pictures> = flow {
        val pictures = jikanService.getPicturesResult(id)
        val item = pictures.toDomain()
        emit(item)
    }

    override suspend fun getRecommendations(id: Int): Flow<Recommendations> = flow {
        val recommendations = jikanService.getRecommendationsResult(id)
        val item = recommendations.toDomain()
        emit(item)
    }

    override suspend fun getReviews(id: Int): Flow<Reviews> = flow {
        val reviews = jikanService.getReviewsResult(id)
        val item = reviews.toDomain()
        emit(item)
    }

    override suspend fun getStats(id: Int): Flow<Stats> = flow {
        val stats = jikanService.getStatsResult(id)
        val item = stats.toDomain()
        emit(item)
    }

    override suspend fun getVideos(id: Int): Flow<Videos> = flow {
        val videos = jikanService.getVideosResult(id)
        val item = videos.toDomain()
        emit(item)
    }

}