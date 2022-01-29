package com.lexwilliam.risutov2.ui.home

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.remote.GetCurrentSeasonAnime
import com.lexwilliam.domain.usecase.remote.GetTopAnime
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.AnimeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getCurrentSeasonAnime: GetCurrentSeasonAnime,
    private val topAnimeUseCase: GetTopAnime,
    private val animeMapper: AnimeMapper
): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): HomeContract.State {
        return HomeContract.State(
            currentSeason = "",
            currentYear = -1,
            seasonAnime = emptyList(),
            topAiringAnime = emptyList(),
            topUpcomingAnime = emptyList(),
            topAnime = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: HomeContract.Event) {
        TODO("Not yet implemented")
    }

    init {
        onCurrentSeasonAnime()
        onTopAnime("airing")
        onTopAnime("upcoming")
        onTopAnime("tv")
    }

    private fun onCurrentSeasonAnime() {
        viewModelScope.launch(errorHandler) {
            try {
                getCurrentSeasonAnime.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        setState {
                            copy(
                                currentSeason = it.season_name,
                                currentYear = it.season_year
                            )
                        }
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        seasonAnime = anime.anime
                                    )
                                }
                            }
                }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun onTopAnime(subType: String) {
        viewModelScope.launch(errorHandler) {
            try {
                topAnimeUseCase.execute(1, subType)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                when(subType) {
                                    "airing" -> setState { copy(topAiringAnime = anime.anime) }
                                    "upcoming" -> setState {  copy(topUpcomingAnime = anime.anime) }
                                    "tv" -> setState { copy(topAnime = anime.anime) }
                                    else -> Timber.tag("subType Unknown")
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
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
}
