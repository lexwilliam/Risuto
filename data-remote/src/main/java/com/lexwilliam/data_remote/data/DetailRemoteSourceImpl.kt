package com.lexwilliam.data_remote.data

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.model.common.*
import com.lexwilliam.data.model.remote.detail.*
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.DetailMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class DetailRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val detailMapper: DetailMapper
): DetailRemoteSource {

    private val _animeDetailSharedFlow = MutableStateFlow(getInitialStateAnimeDetail())
    private val animeDetailSharedFlow = _animeDetailSharedFlow.asSharedFlow()
    private val _characterStaffSharedFlow = MutableStateFlow(getInitialStateCharacterStaff())
    private val characterStaffSharedFlow = _characterStaffSharedFlow.asSharedFlow()
    private val _episodesSharedFlow = MutableStateFlow(getInitialStateEpisodes())
    private val episodesSharedFlow = _episodesSharedFlow.asSharedFlow()
    private val _forumSharedFlow = MutableStateFlow(getInitialStateForum())
    private val forumSharedFlow = _forumSharedFlow.asSharedFlow()
    private val _moreInfoSharedFlow = MutableStateFlow(getInitialStateMoreInfo())
    private val moreInfoSharedFlow = _moreInfoSharedFlow.asSharedFlow()
    private val _newsSharedFlow = MutableStateFlow(getInitialStateNews())
    private val newsSharedFlow = _newsSharedFlow.asSharedFlow()
    private val _picturesSharedFlow = MutableStateFlow(getInitialStatePictures())
    private val picturesSharedFlow = _picturesSharedFlow.asSharedFlow()
    private val _recommendationsSharedFlow = MutableStateFlow(getInitialStateRecommendations())
    private val recommendationsSharedFlow = _recommendationsSharedFlow.asSharedFlow()
    private val _reviewsSharedFlow = MutableStateFlow(getInitialStateReviews())
    private val reviewsSharedFlow = _reviewsSharedFlow.asSharedFlow()
    private val _statsSharedFlow = MutableStateFlow(getInitialStateStats())
    private val statsSharedFlow = _statsSharedFlow.asSharedFlow()
    private val _videosSharedFlow = MutableStateFlow(getInitialStateVideos())
    private val videosSharedFlow = _videosSharedFlow.asSharedFlow()

    override suspend fun anime(id: Int): Flow<AnimeDetailRepo> {
        try {
            detailMapper.toRepo(jikanService.getAnimeResult(id))
                .let { anime ->
                    _animeDetailSharedFlow.emit(anime)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return animeDetailSharedFlow.distinctUntilChanged()
    }

    override suspend fun characterStaff(id: Int): Flow<CharacterStaffRepo> {
        try {
            detailMapper.toRepo(jikanService.getCharacterStaffResult(id))
                .let { anime ->
                    _characterStaffSharedFlow.emit(anime)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return characterStaffSharedFlow.distinctUntilChanged()
    }

    override suspend fun episodes(id: Int): Flow<EpisodesRepo> {
        try {
            detailMapper.toRepo(jikanService.getEpisodesResult(id))
                .let { episodes ->
                    _episodesSharedFlow.emit(episodes)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return episodesSharedFlow.distinctUntilChanged()
    }

    override suspend fun forum(id: Int): Flow<ForumRepo> {
        try {
            detailMapper.toRepo(jikanService.getForumResult(id))
                .let { forum ->
                    _forumSharedFlow.emit(forum)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return forumSharedFlow.distinctUntilChanged()
    }

    override suspend fun moreInfo(id: Int): Flow<MoreInfoRepo> {
        try {
            detailMapper.toRepo(jikanService.getMoreInfoResult(id))
                .let { moreInfo ->
                    _moreInfoSharedFlow.emit(moreInfo)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return moreInfoSharedFlow.distinctUntilChanged()
    }

    override suspend fun news(id: Int): Flow<NewsRepo> {
        try {
            detailMapper.toRepo(jikanService.getNewsResult(id))
                .let { news ->
                    _newsSharedFlow.emit(news)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return newsSharedFlow.distinctUntilChanged()
    }

    override suspend fun pictures(id: Int): Flow<PicturesRepo> {
        try {
            detailMapper.toRepo(jikanService.getPicturesResult(id))
                .let { pictures ->
                    _picturesSharedFlow.emit(pictures)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return picturesSharedFlow.distinctUntilChanged()
    }

    override suspend fun recommendations(id: Int): Flow<RecommendationsRepo> {
        try {
            detailMapper.toRepo(jikanService.getRecommendationsResult(id))
                .let { recommendations ->
                    _recommendationsSharedFlow.emit(recommendations)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return recommendationsSharedFlow.distinctUntilChanged()
    }

    override suspend fun reviews(id: Int): Flow<ReviewsRepo> {
        try {
            detailMapper.toRepo(jikanService.getReviewsResult(id))
                .let { reviews ->
                    _reviewsSharedFlow.emit(reviews)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return reviewsSharedFlow.distinctUntilChanged()
    }

    override suspend fun stats(id: Int): Flow<StatsRepo> {
        try {
            detailMapper.toRepo(jikanService.getStatsResult(id))
                .let { stats ->
                    _statsSharedFlow.emit(stats)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return statsSharedFlow.distinctUntilChanged()
    }

    override suspend fun videos(id: Int): Flow<VideosRepo> {
        try {
            detailMapper.toRepo(jikanService.getVideosResult(id))
                .let { videos ->
                    _videosSharedFlow.emit(videos)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return videosSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateAnimeDetail() =
        AnimeDetailRepo(
            AiredRepo("", PropRepo(FromRepo(-1,-1,-1), ToRepo(-1,-1,-1)),"", ""),
            false, "", "", "", listOf(""), -1,
            -1, listOf(GenreRepo(-1,"", "", "")), "",
            listOf(LicensorRepo(-1, "", "", "")), -1,-1,
            listOf(""), -1, "", listOf(ProducerRepo(-1, "","", "" )),
            -1, "", RelatedRepo(), -1, false, "", 0.0, -1,
            "", "", listOf(StudioRepo(-1, "", "", "")), "",
            "", "", "", listOf(""), "", "", ""
        )

    private fun getInitialStateCharacterStaff() =
        CharacterStaffRepo(
            emptyList(), -1, false, "", emptyList()
        )

    private fun getInitialStateEpisodes() =
        EpisodesRepo(emptyList(), -1, -1, false, "")

    private fun getInitialStateForum() =
        ForumRepo(-1, false, "", emptyList())

    private fun getInitialStateMoreInfo() =
        MoreInfoRepo("", -1, false, "")

    private fun getInitialStateNews() =
        NewsRepo(emptyList(), -1, false, "")

    private fun getInitialStatePictures() =
        PicturesRepo(emptyList(), -1, false, "")

    private fun getInitialStateRecommendations() =
        RecommendationsRepo(emptyList(), -1, false, "")

    private fun getInitialStateReviews() =
        ReviewsRepo(-1, false, "", emptyList())

    private fun getInitialStateStats() =
        StatsRepo(-1, -1, -1, -1, -1, false, "", ReviewScoreRepo(-1, -1,-1, -1, -1, -1), -1, -1)

    private fun getInitialStateVideos() =
        VideosRepo(emptyList(), emptyList(), -1, false, "")
}