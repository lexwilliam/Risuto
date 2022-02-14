package com.lexwilliam.risuto.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.remote.*
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
    private val getTokenInfo: GetTokenInfo,
    private val getUserInfo: GetUserInfo,
    private val refreshToken: RefreshToken,
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
            isTokenValid = null,
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
//        setupOAuth()
//        if(viewState.value.isTokenValid != null) {
//            if(viewState.value.isTokenValid!!) {
//
//            }
//        }
        viewModelScope.launch {
            getUserInfo()
            onAiringToday()
            onTopAiring()
            onTopUpcoming()
            onTopAnime()
        }

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

    private fun onTopAiring() {
        viewModelScope.launch(errorHandler) {
            try {
                getTopAnime.execute(1, "airing")
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState { copy(topAiringAnime = anime.anime) }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun onTopUpcoming() {
        viewModelScope.launch(errorHandler) {
            try {
                getTopAnime.execute(1, "upcoming")
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {  copy(topUpcomingAnime = anime.anime) }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun onTopAnime() {
        viewModelScope.launch(errorHandler) {
            try {
                getTopAnime.execute(1, "tv")
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState { copy(topAnime = anime.anime) }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun setupOAuth() {
        viewModelScope.launch(errorHandler) {
            val currentTime = System.currentTimeMillis()
            val token = getTokenInfo.execute()
            Timber.d("tokenInfo : $token")
            if(token.expiresIn != null) {
                if(token.expiresIn!! < currentTime) {
                    refreshToken()
                }
                setState { copy(isTokenValid = true) }
            } else {
                setState { copy(isTokenValid = false) }
            }
        }
    }

    private fun refreshToken() {
        viewModelScope.launch(errorHandler) {
            refreshToken.execute()
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch(errorHandler) {
            val name = getUserInfo.execute()
            if(name == null) {
                setState { copy(username = "") }
            } else {
                setState { copy(username = name) }
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
