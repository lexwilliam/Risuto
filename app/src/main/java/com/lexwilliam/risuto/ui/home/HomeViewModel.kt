package com.lexwilliam.risuto.ui.home

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.remote.GetCurrentSeasonAnime
import com.lexwilliam.domain.usecase.remote.GetSearchAnime
import com.lexwilliam.domain.usecase.remote.GetTopAnime
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.util.getCurrentDate
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
    private val getCurrentSeasonAnime: GetCurrentSeasonAnime,
    private val getSearchAnime: GetSearchAnime,
    private val getTopAnime: GetTopAnime,
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
            airingTodayAnime = emptyList(),
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
        onAiringToday()
        onCurrentSeasonAnime()
        onTopAnime("airing")
        onTopAnime("upcoming")
        onTopAnime("tv")
        setState { copy(isLoading = false) }
    }

    private fun onAiringToday() {
        viewModelScope.launch(errorHandler) {
            try {
                getSearchAnime.execute(null, null, "airing", null, null, "members", "desc", null)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                val filterToday = anime.anime.filter { convertDateToNameOfDay(it.start_date!!) == getCurrentDate() }
                                setState {
                                    copy(
                                        airingTodayAnime = filterToday,
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
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
                getTopAnime.execute(1, subType)
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

    private fun convertDateToNameOfDay(date: String): String {
        val onlyDate = date.removeRange(date.indexOf("T"), date.length)
        return DateTime.parse(onlyDate).dayOfWeek().getAsText(Locale.ROOT)
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
