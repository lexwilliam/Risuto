package com.lexwilliam.risuto.ui.screens.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetUserAnimeList
import com.lexwilliam.domain.usecase.GetUserInfo
import com.lexwilliam.domain.usecase.UpdateUserAnimeStatus
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.model.WatchStatusPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyAnimeViewModel
@Inject constructor(
    private val getUserInfo: GetUserInfo,
    private val getUserAnimeList: GetUserAnimeList,
    private val updateUserAnimeStatus: UpdateUserAnimeStatus,
    private val animeMapper: AnimeMapper
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
        getUserInfo()
        getUserAnimeList()
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