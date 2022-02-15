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
    private val getRefreshTokenFromCache: GetRefreshTokenFromCache,
    private val getExpiresInFromCache: GetExpiresInFromCache,
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

    private var accessTokenFlow: MutableStateFlow<String> = MutableStateFlow("")

    init {
        setupOAuth()
        getAccessTokenFromCache()
        getUserInfo(accessTokenFlow.value)
        onAiringToday()
        onTopAiring()
        onTopUpcoming()
        onTopAnime()
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

    private fun setupOAuth() {
        viewModelScope.launch(errorHandler) {
            getExpiresInFromCache()
            Timber.d("expire : ${expiresInFlow.value}")
            if(expiresInFlow.value < System.currentTimeMillis()) {
                getRefreshTokenFromCache()
                Timber.d("refresh : ${refreshTokenFlow.value}")
                refreshToken.execute(BuildConfig.CLIENT_ID, refreshTokenFlow.value)
                setState { copy(isTokenValid = true) }
            } else {
                setState { copy(isTokenValid = false) }
            }
        }
    }

    private fun getAccessTokenFromCache() {
        viewModelScope.launch(errorHandler) {
            getAccessTokenFromCache.execute().collect {
                if(it != null) {
                    accessTokenFlow.value = it
                } else {
                    Timber.d("Access Token Not Found")
                }
            }
        }
    }

    private fun getRefreshTokenFromCache() {
        viewModelScope.launch(errorHandler) {
            getRefreshTokenFromCache.execute().collect {
                if(it != null) {
                    refreshTokenFlow.value = it
                } else {
                    Timber.d("Refresh Token Not Found")
                }
            }
        }
    }

    private fun getExpiresInFromCache() {
        viewModelScope.launch(errorHandler) {
            getExpiresInFromCache.execute().collect {
                if(it != null) {
                    expiresInFlow.value = it
                } else {
                    Timber.d("Expires In Not Found")
                }
            }
        }
    }

    private fun getUserInfo(accessToken: String) {
        viewModelScope.launch(errorHandler) {
            Timber.d("access : ${accessTokenFlow.value}")
            val name = getUserInfo.execute(accessTokenFlow.value)
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
