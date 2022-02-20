package com.lexwilliam.risuto.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.InsertAnimeHistory
import com.lexwilliam.domain.usecase.remote.*
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.DetailMapper
import com.lexwilliam.risuto.mapper.HistoryMapper
import com.lexwilliam.risuto.model.common.*
import com.lexwilliam.risuto.model.detail.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeDetail: GetAnimeDetail,
    private val getCharacterStaff: GetCharacterStaff,
    private val getEpisodes: GetEpisodes,
    private val getForum: GetForum,
    private val getMoreInfo: GetMoreInfo,
    private val getPictures: GetPictures,
    private val getRecommendations: GetRecommendations,
    private val getReviews: GetReviews,
    private val getStats: GetStats,
    private val getVideos: GetVideos,
    private val insertAnimeHistory: InsertAnimeHistory,
    private val detailMapper: DetailMapper,
    private val historyMapper: HistoryMapper,
    savedState: SavedStateHandle
): BaseViewModel<AnimeContract.Event, AnimeContract.State, AnimeContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    override fun setInitialState(): AnimeContract.State {
        return AnimeContract.State(
            animeDetail = getInitialStateAnimeDetail(),
            characterStaff = getInitialStateCharacterStaff(),
            episodes = getInitialStateEpisodes(),
            forum = getInitialStateForum(),
            moreInfo = getInitialStateMoreInfo(),
            pictures = getInitialStatePictures(),
            recommendations = getInitialStateRecommendations(),
            reviews = getInitialStateReviews(),
            stats = getInitialStateStats(),
            videos = getInitialStateVideos(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: AnimeContract.Event) {
        when(event) {
            is AnimeContract.Event.InsertAnimeHistory -> insertAnimeHistory(event.anime)
        }
    }

    init {
        viewModelScope.launch(errorHandler) {
            malIdFromArgs?.let { id ->
                animeDetail(id)
                characterStaff(id)
                delay(3000)
                episodes(id)
                forum(id)
                delay(3000)
                moreInfo(id)
                pictures(id)
                delay(4000)
                recommendations(id)
                reviews(id)
                delay(3000)
                stats(id)
                videos(id)
            }
        }
    }

    private fun animeDetail(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getAnimeDetail.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { detail ->
                        detailMapper.toPresentation(detail)
                            .let { animeDetail ->
                                setState {
                                    copy(
                                        animeDetail = animeDetail,
                                        isLoading = false
                                    )
                                }
                                insertAnimeHistory(animeDetail)
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun characterStaff(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getCharacterStaff.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { characterStaff ->
                                setState {
                                    copy(
                                        characterStaff = characterStaff
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun episodes(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getEpisodes.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { episodes ->
                                setState {
                                    copy(
                                        episodes = episodes
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun forum(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getForum.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { forum ->
                                setState {
                                    copy(
                                        forum = forum
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun moreInfo(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getMoreInfo.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { moreInfo ->
                                setState {
                                    copy(
                                        moreInfo = moreInfo
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun pictures(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getPictures.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { pictures ->
                                setState {
                                    copy(
                                        pictures = pictures
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun recommendations(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getRecommendations.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { recommendations ->
                                setState {
                                    copy(
                                        recommendations = recommendations
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun reviews(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getReviews.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { reviews ->
                                setState {
                                    copy(
                                        reviews = reviews
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun stats(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getStats.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { stats ->
                                setState {
                                    copy(
                                        stats = stats
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun videos(mal_id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getVideos.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { videos ->
                                setState {
                                    copy(
                                        videos = videos
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun insertAnimeHistory(anime: AnimeDetailPresentation) {
        viewModelScope.launch(errorHandler) {
            insertAnimeHistory.execute(historyMapper.toDomain(anime))
        }
    }

    private fun handleExceptions(throwable: Throwable) {
        Timber.e(throwable)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private fun getInitialStateAnimeDetail() =
        AnimeDetailPresentation(
            AiredPresentation("", PropPresentation(FromPresentation(-1,-1,-1), ToPresentation(-1,-1,-1)),"", ""),
            false, "", "", "", listOf(""), -1,
            -1, listOf(GenrePresentation(-1,"", "", "")), "",
            listOf(LicensorPresentation(-1, "", "", "")), -1,-1,
            listOf(""), -1, "", listOf(ProducerPresentation(-1, "","", "" )),
            -1, "", RelatedPresentation(), -1, false, "", 0.0, -1,
            "", "", listOf(StudioPresentation(-1, "", "", "")), "",
            "", "", "", listOf(""), "", "", ""
        )

    private fun getInitialStateCharacterStaff() =
        CharacterStaffPresentation(emptyList(), emptyList())

    private fun getInitialStateEpisodes() =
        EpisodesPresentation(emptyList(), -1)

    private fun getInitialStateForum() =
        ForumPresentation(emptyList())

    private fun getInitialStateMoreInfo() =
        MoreInfoPresentation("")

    private fun getInitialStatePictures() =
        PicturesPresentation(emptyList())

    private fun getInitialStateRecommendations() =
        RecommendationsPresentation(emptyList())

    private fun getInitialStateReviews() =
        ReviewsPresentation(emptyList())

    private fun getInitialStateStats() =
        StatsPresentation(-1, -1, -1, -1, ReviewScorePresentation(-1 , -1, -1, -1, -1, -1), -1, -1)

    private fun getInitialStateVideos() =
        VideosPresentation(emptyList(), emptyList())
}