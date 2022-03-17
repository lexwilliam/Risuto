package com.lexwilliam.risuto.ui.screens.season

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetSeason
import com.lexwilliam.domain.usecase.GetSeasonList
import com.lexwilliam.domain.usecase.GetSeasonNow
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.model.SeasonListPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val getSeasonNow: GetSeasonNow,
    private val getSeason: GetSeason,
    private val getSeasonList: GetSeasonList,
    private val animeMapper: AnimeMapper
    ): BaseViewModel<SeasonContract.Event, SeasonContract.State, SeasonContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): SeasonContract.State {
        return SeasonContract.State(
            season = "",
            year = -1,
            seasonList = SeasonListPresentation(emptyList()),
            seasonAnime = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: SeasonContract.Event) {
        when(event) {
            is SeasonContract.Event.SetSeason -> {
                setState {
                    copy(
                        season = event.season,
                        year = event.year,
                        seasonAnime = emptyList()
                    )
                }
                getSeason(event.year, event.season)
            }
            is SeasonContract.Event.RefreshList -> {
                viewModelScope.launch(errorHandler) {
                    setState { copy(isRefreshing = true) }
                    getSeason(event.year, event.season)
                    delay(1000)
                    setState { copy(isRefreshing = false) }
                }
            }
        }
    }

    init {
        getSeasonList()
        getSeasonNow()
    }

    private fun getSeasonNow() {
        viewModelScope.launch(errorHandler) {
            try {
                getSeasonNow.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        seasonAnime = anime.data,
                                        season = anime.data.first().season,
                                        year = anime.data.first().year,
                                        isLoading = false
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun getSeason(year: Int, season: String) {
        viewModelScope.launch(errorHandler) {
            try {
                getSeason.execute(year, season.replaceFirstChar { it.lowercase(Locale.ROOT) })
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        seasonAnime = anime.data,
                                        season = anime.data.first().season,
                                        year = anime.data.first().year
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun getSeasonList() {
        viewModelScope.launch(errorHandler) {
            try {
                getSeasonList.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { list ->
                                setState {
                                    copy(
                                        seasonList = list
                                    )
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