package com.lexwilliam.risuto.ui.screens.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetUserAnimeList
import com.lexwilliam.domain.usecase.GetUserInfo
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val getUserInfo: GetUserInfo,
    private val getUserAnimeList: GetUserAnimeList,
    private val animeMapper: AnimeMapper
) : BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): ProfileContract.State {
        return ProfileContract.State(
            animes = emptyList(),
            username = "",
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: ProfileContract.Event) {
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