package com.lexwilliam.risuto

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetExpiresInFromCache
import com.lexwilliam.domain.usecase.GetRefreshTokenFromCache
import com.lexwilliam.domain.usecase.SetRefreshToken
import com.lexwilliam.risuto.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RisutoAppViewModel @Inject constructor(
    private val getRefreshTokenFromCache: GetRefreshTokenFromCache,
    private val getExpiresInFromCache: GetExpiresInFromCache,
    private val setRefreshAccessToken: SetRefreshToken
): BaseViewModel<RisutoContract.Event, RisutoContract.State, RisutoContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): RisutoContract.State {
        return RisutoContract.State(
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: RisutoContract.Event) {}

    init {
        viewModelScope.launch(errorHandler) {
            val expiresIn = getExpiresInFromCache.execute().firstOrNull()
            val refreshToken = getRefreshTokenFromCache.execute().firstOrNull()
            if(refreshToken == "" && expiresIn == -1L) {
                setState { copy(isUserLoggedIn = false) }
            } else {
                if(refreshToken != null && expiresIn != null) {
                    if(expiresIn < System.currentTimeMillis()) {
                        Timber.d("currentTime : ${System.currentTimeMillis()}")
                        Timber.d("expire : $expiresIn")
                        Timber.d("refresh : $refreshToken")
                        refreshAccessToken(refreshToken)
                    }
                } else {
                    Timber.d("Refresh Token or Expire In Not Found ")
                }
                setState { copy(isUserLoggedIn = true) }
            }
            setState { copy(isLoading = false) }
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