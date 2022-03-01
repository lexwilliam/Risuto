package com.lexwilliam.risuto.ui.screens.splash

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetExpiresInFromCache
import com.lexwilliam.domain.usecase.GetRefreshTokenFromCache
import com.lexwilliam.domain.usecase.SetRefreshToken
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
    private val setRefreshAccessToken: SetRefreshToken
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
            isUserLoggedIn = null,
            refreshToken = "",
            expiresIn = -1L,
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: SplashContract.Event) {
        when(event) {
            is SplashContract.Event.SetupOAuth ->
               checkUserSignInState( event.refreshToken, event.expiresIn)
        }
    }

    init {
        getRefreshTokenFromCache()
        getExpiresInFromCache()
    }

    private fun checkUserSignInState(refreshToken: String?, expiresIn: Long) {
        viewModelScope.launch(errorHandler) {
            if(refreshToken == "" && expiresIn == -1L) {
                setState { copy(isUserLoggedIn = false) }
            } else {
                if(refreshToken != null) {
                    if(expiresIn < System.currentTimeMillis()) {
                        Timber.d("currentTime : ${System.currentTimeMillis()}")
                        Timber.d("expire : $expiresIn")
                        Timber.d("refresh : $refreshToken")
                        refreshAccessToken(refreshToken)
                    }
                } else {
                    Timber.d("Refresh Token Not Found")
                }
                setState { copy(isUserLoggedIn = true) }
            }
        }
    }

    private fun getRefreshTokenFromCache() {
        viewModelScope.launch(errorHandler) {
            getRefreshTokenFromCache.execute().collect {
                setState { copy(refreshToken = it) }
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
                setState { copy(isLoading = false) }
            }
        }
    }

    private fun refreshAccessToken(refreshToken: String) {
        viewModelScope.launch(errorHandler) {
            setRefreshAccessToken.execute(
                clientId = BuildConfig.CLIENT_ID,
                refreshToken = refreshToken
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