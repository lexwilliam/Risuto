package com.lexwilliam.risuto.ui.screens.splash

import androidx.lifecycle.viewModelScope
import com.lexwilliam.risuto.BuildConfig
import com.lexwilliam.risuto.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

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
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: SplashContract.Event) {
        TODO("Not yet implemented")
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