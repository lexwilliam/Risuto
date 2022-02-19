package com.lexwilliam.risuto.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.GetAccessTokenFromCache
import com.lexwilliam.domain.usecase.local.GetExpiresInFromCache
import com.lexwilliam.domain.usecase.local.GetRefreshTokenFromCache
import com.lexwilliam.domain.usecase.remote.*
import com.lexwilliam.risuto.BuildConfig
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val getAccessTokenFromCache: GetAccessTokenFromCache,
    private val getUserInfo: GetUserInfo,
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
        when(event) {
            is HomeContract.Event.LoadingDone -> {
                setState { copy(isLoading = false) }
            }
            is HomeContract.Event.GetUserInfo -> {
                getUserInfo(event.accessToken)
            }
        }
    }

    init {
        getAccessTokenFromCache()
        onAiringToday()
        onTopAiring()
        onTopUpcoming()
        onTopAnime()
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

//    private fun onCurrentSeasonAnime() {
//        viewModelScope.launch(errorHandler) {
//            try {
//                getCurrentSeasonAnime.execute()
//                    .catch { throwable ->
//                        handleExceptions(throwable)
//                    }
//                    .collect {
//                        setState {
//                            copy(
//                                currentSeason = it.season_name,
//                                currentYear = it.season_year
//                            )
//                        }
//                        animeMapper.toPresentation(it)
//                            .let { anime ->
//                                setState {
//                                    copy(
//                                        seasonAnime = anime.anime
//                                    )
//                                }
//                            }
//                    }
//            } catch (throwable: Throwable) {
//                handleExceptions(throwable)
//            }
//        }
//    }

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

    private fun getAccessTokenFromCache() {
        viewModelScope.launch(errorHandler) {
            getAccessTokenFromCache.execute().collect {
                if(it != null) {
                    setState { copy(accessToken = it) }
                } else {
                    Timber.d("Access Token Not Found")
                }
            }
        }
    }

    private fun getUserInfo(accessToken: String) {
        viewModelScope.launch(errorHandler) {
            Timber.d("access : $accessToken")
            val name = getUserInfo.execute(accessToken)
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
