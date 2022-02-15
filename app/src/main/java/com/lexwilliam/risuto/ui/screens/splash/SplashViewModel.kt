package com.lexwilliam.risuto.ui.screens.splash

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.GetAccessTokenFromCache
import com.lexwilliam.domain.usecase.local.GetExpiresInFromCache
import com.lexwilliam.domain.usecase.local.GetRefreshTokenFromCache
import com.lexwilliam.domain.usecase.remote.RefreshToken
import com.lexwilliam.risuto.BuildConfig
import com.lexwilliam.risuto.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getRefreshTokenFromCache: GetRefreshTokenFromCache,
    private val getExpiresInFromCache: GetExpiresInFromCache,
    private val refreshToken: RefreshToken
): BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): SplashContract.State {
        return SplashContract.State(
            isTokenValid = null,
            accessToken = "",
            refreshToken = "",
            expiresIn = -1L,
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: SplashContract.Event) {
        when(event) {
            is SplashContract.Event.SetupOAuth ->
               setupOAuth(event.accessToken, event.refreshToken, event.expiresIn)
        }
    }

    init {
        getRefreshTokenFromCache()
        getExpiresInFromCache()

    }

    private fun setupOAuth(accessToken: String, refreshToken: String, expiresIn: Long) {
        viewModelScope.launch(errorHandler) {
            Timber.d("expire : ${viewState.value.expiresIn}")
            if(viewState.value.expiresIn < System.currentTimeMillis()) {
                Timber.d("refresh : ${viewState.value.refreshToken}")
                refreshToken()
                setState { copy(isTokenValid = true) }
            } else {
                setState { copy(isTokenValid = false) }
            }
        }
    }

    private fun getRefreshTokenFromCache() {
        viewModelScope.launch(errorHandler) {
            getRefreshTokenFromCache.execute().collect {
                if(it != null) {
                    setState { copy(refreshToken = it) }
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
                    setState { copy(expiresIn = it) }
                } else {
                    Timber.d("Expires In Not Found")
                }
            }
            setState { copy(isLoading = false) }
        }
    }

    private fun refreshToken() {
        viewModelScope.launch(errorHandler) {
            refreshToken.execute(
                clientId = BuildConfig.CLIENT_ID,
                refreshToken = viewState.value.refreshToken
            )
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