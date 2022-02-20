package com.lexwilliam.risuto.ui.screens.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.GetAccessTokenFromCache
import com.lexwilliam.domain.usecase.remote.GetUserAnimeList
import com.lexwilliam.domain.usecase.remote.GetUserInfo
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
    private val getAccessTokenFromCache: GetAccessTokenFromCache,
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
            accessToken = "",
            username = "",
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: ProfileContract.Event) {
        when(event) {
            is ProfileContract.Event.GetUserInfo -> {
                getUserInfo(event.accessToken)
            }
            is ProfileContract.Event.GetUserAnimeList -> {
                getUserAnimeList(event.accessToken)
                setState { copy(isLoading = false) }
            }
        }
    }

    init {
        getAccessTokenFromCache()
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

    private fun getUserAnimeList(accessToken: String) {
        viewModelScope.launch(errorHandler) {
            getUserAnimeList.execute(accessToken).collect { animes ->
                setState { copy(animes = animes.data.map { animeMapper.toPresentation(it) }) }
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