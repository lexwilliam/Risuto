package com.lexwilliam.risuto.ui.screens.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetAccessTokenFromCache
import com.lexwilliam.domain.usecase.GetUserAnimeList
import com.lexwilliam.domain.usecase.GetUserProfile
import com.lexwilliam.domain.usecase.UpdateUserAnimeStatus
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.mapper.UserMapper
import com.lexwilliam.risuto.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyAnimeViewModel
@Inject constructor(
    private val getUserProfile: GetUserProfile,
    private val getUserAnimeList: GetUserAnimeList,
    private val updateUserAnimeStatus: UpdateUserAnimeStatus,
    private val animeMapper: AnimeMapper,
    private val userMapper: UserMapper,
    private val getAccessTokenFromCache: GetAccessTokenFromCache,
) : BaseViewModel<MyAnimeContract.Event, MyAnimeContract.State, MyAnimeContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): MyAnimeContract.State {
        return MyAnimeContract.State(
            animes = emptyList(),
            username = "",
            userImage = "",
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: MyAnimeContract.Event) {
        when(event) {
            is MyAnimeContract.Event.RefreshList -> {
                viewModelScope.launch(errorHandler) {
                    setState { copy(isRefreshing = true) }
                    getUserAnimeList()
                    delay(1000)
                    setState { copy(isRefreshing = false) }
                }
            }
            is MyAnimeContract.Event.RefreshListWithoutView -> {
                viewModelScope.launch(errorHandler) {
                    getUserAnimeList()
                }
            }
            is MyAnimeContract.Event.UpdateUserAnimeStatus -> {
                updateUserAnimeStatus(event.id, event.numEpisodesWatched, event.status, event.score)
            }
        }
    }

    init {
        viewModelScope.launch(errorHandler) {
            val accessToken = getAccessTokenFromCache.execute().firstOrNull()
            if (accessToken == "GUEST") {
                setState { copy(isGuest = true, isLoading = false) }
                return@launch
            }
            getUserProfile()
            getUserAnimeList()
        }
    }

    private fun getUserAnimeList() {
        viewModelScope.launch(errorHandler) {
            getUserAnimeList.execute().collect { animes ->
                setState {
                    copy(
                        animes = animes.data.map { animeMapper.toPresentation(it) },
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch(errorHandler) {
            try {
                getUserProfile.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        userMapper.toPresentation(it)
                            .let { profile ->
                                setState {
                                    copy(
                                        username = profile.data.username,
                                        userImage = profile.data.images.jpg.image_url
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun updateUserAnimeStatus(id: Int, numEpisodesWatched: Int, status: String, score: Int) {
        viewModelScope.launch(errorHandler) {
            updateUserAnimeStatus.execute(id, numEpisodesWatched, status, score).collect()
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