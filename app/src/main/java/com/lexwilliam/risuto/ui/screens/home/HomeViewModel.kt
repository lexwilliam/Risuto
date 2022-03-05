package com.lexwilliam.risuto.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetSchedules
import com.lexwilliam.domain.usecase.GetSeasonNow
import com.lexwilliam.domain.usecase.GetTopAnime
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getTopAnime: GetTopAnime,
    private val getSchedules: GetSchedules,
    private val getSeasonNow: GetSeasonNow,
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
            username = "",
            accessToken = "",
            currentSeason = "",
            currentYear = -1,
            seasonAnime = emptyList(),
            topAnime = emptyList(),
            schedules = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.LoadingDone -> {
                setState { copy(isLoading = false) }
            }
        }
    }

    init {
        getSchedules()
        getSeasonNow()
        getTopAnime()
    }

    private fun getSchedules() {
        viewModelScope.launch(errorHandler) {
            try {
                getSchedules.execute(getCurrentDayOfWeek().replaceFirstChar { it.lowercase(Locale.ROOT) })
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState { copy(schedules = anime.data.sortedByDescending { item -> item.members }) }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
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
                                        currentSeason = anime.data.first().season,
                                        currentYear = anime.data.first().year
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun getTopAnime() {
        viewModelScope.launch(errorHandler) {
            try {
                getTopAnime.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState { copy(topAnime = anime.data) }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun convertDateToNameOfDay(date: String): String {
        val onlyDate = date.removeRange(date.indexOf("T"), date.length)
        return DateTime.parse(onlyDate).dayOfWeek().getAsText(Locale.ROOT)
    }

    private fun getCurrentDayOfWeek(): String =
        DateTime.now().dayOfWeek().asText

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
