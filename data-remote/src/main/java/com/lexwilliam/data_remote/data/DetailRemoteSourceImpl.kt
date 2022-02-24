package com.lexwilliam.data_remote.data

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.model.common.jikan.*
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.detail.*
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.DetailMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val malService: MyAnimeListService,
    private val detailMapper: DetailMapper
): DetailRemoteSource {

    override suspend fun anime(id: Int): Flow<AnimeDetailRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getAnimeResult(id)))
    }

    override suspend fun characterStaff(id: Int): Flow<CharacterStaffRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getCharacterStaffResult(id)))
    }

    override suspend fun episodes(id: Int): Flow<EpisodesRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getEpisodesResult(id)))
    }

    override suspend fun forum(id: Int): Flow<ForumRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getForumResult(id)))
    }

    override suspend fun moreInfo(id: Int): Flow<MoreInfoRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getMoreInfoResult(id)))
    }

    override suspend fun news(id: Int): Flow<NewsRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getNewsResult(id)))
    }

    override suspend fun pictures(id: Int): Flow<PicturesRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getPicturesResult(id)))
    }

    override suspend fun recommendations(id: Int): Flow<RecommendationsRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getRecommendationsResult(id)))
    }

    override suspend fun reviews(id: Int): Flow<ReviewsRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getReviewsResult(id)))
    }

    override suspend fun stats(id: Int): Flow<StatsRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getStatsResult(id)))
    }

    override suspend fun videos(id: Int): Flow<VideosRepo> = flow {
        emit(detailMapper.toRepo(jikanService.getVideosResult(id)))
    }

    override suspend fun status(accessToken: String, id: Int): Flow<MyAnimeStatusRepo> = flow {
        emit(detailMapper.toRepo(malService.getMyAnimeStatus(accessToken, id).body()!!))
    }
}