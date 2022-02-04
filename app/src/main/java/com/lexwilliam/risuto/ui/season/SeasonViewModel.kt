package com.lexwilliam.risuto.ui.season

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.remote.GetCurrentSeasonAnime
import com.lexwilliam.domain.usecase.remote.GetSeasonAnime
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel
    @Inject constructor(
        private val getSeasonAnime: GetSeasonAnime,
        private val getCurrentSeasonAnime: GetCurrentSeasonAnime,
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
            seasonAnimes = emptyList(),
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
                        isLoading = true,
                        seasonAnimes = emptyList()
                    )
                }
                onSeasonAnime(event.season, event.year)
            }

        }
    }

    init {
        onCurrentSeasonAnime()
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
                                season = it.season_name,
                                year = it.season_year
                            )
                        }
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        seasonAnimes = anime.anime,
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

    private fun onSeasonAnime(season: String, year: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getSeasonAnime.execute(year, season.replaceFirstChar { it.lowercase(Locale.ROOT) })
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        setState {
                            copy(
                                season = it.season_name,
                                year = it.season_year
                            )
                        }
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        seasonAnimes = anime.anime,
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