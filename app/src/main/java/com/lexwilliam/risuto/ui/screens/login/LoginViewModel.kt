package com.lexwilliam.risuto.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetAuthTokenLink
import com.lexwilliam.domain.usecase.GetCodeChallenge
import com.lexwilliam.domain.usecase.SetAccessToken
import com.lexwilliam.domain.usecase.SetCodeChallenge
import com.lexwilliam.risuto.BuildConfig
import com.lexwilliam.risuto.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setAccessToken: SetAccessToken,
    private val getAuthTokenLink: GetAuthTokenLink,
    private val getCodeChallenge: GetCodeChallenge,
    private val setCodeChallenge: SetCodeChallenge
): BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): LoginContract.State {
        return LoginContract.State(
            oAuthState = OAuthState.Idle,
            authTokenLink = "",
            codeChallenge = "",
            state = "",
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.RedirectToAuth -> {
                getAuthTokenLink()
                setState {
                    copy(
                        oAuthState = OAuthState.RedirectToAuth(
                            codeChallenge = viewState.value.codeChallenge,
                            state = viewState.value.state
                        )
                    )
                }
            }
            is LoginContract.Event.ReceivedAuthToken -> {
                receivedAuthToken(event.code)
                setState { copy(oAuthState = OAuthState.CodeGotten) }
            }
            is LoginContract.Event.Done -> {
                setState { copy(oAuthState = OAuthState.Done) }
            }
        }
    }

    private var codeChallenge: String? = null
    private var state: String = "Risuto"


    init {
        getCodeChallenge()
    }

    private fun setCodeChallenge(codeChallenge: String?) {
        viewModelScope.launch(errorHandler) {
            setCodeChallenge.execute(codeChallenge)
        }
    }

    private fun getCodeChallenge() {
        viewModelScope.launch(errorHandler) {
            getCodeChallenge.execute().collect {
                Timber.d("codeVerifier : $it")
                codeChallenge = it
                if (codeChallenge == null) {
                    codeChallenge = getCodeChallengeString()
                    setCodeChallenge(codeChallenge)
                }
                setState { copy(isLoading = false) }
            }
        }
    }

    private fun getAuthTokenLink() {
        viewModelScope.launch(errorHandler) {
            val link = getAuthTokenLink.execute(BuildConfig.CLIENT_ID, state, codeChallenge!!)
            Timber.d("Link : $link")
            setState {
                copy(
                    authTokenLink = link
                )
            }
        }
    }

    private fun getCodeChallengeString(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..128)
            .map { charPool[Random.nextInt(0, charPool.size)] }
            .joinToString("")
    }

    private fun receivedAuthToken(code: String) {
        viewModelScope.launch(errorHandler) {
            Timber.d("challenge retrieved: $codeChallenge")
            Timber.d("code received: $code")
            setAccessToken.execute(BuildConfig.CLIENT_ID, code, codeChallenge!!)
            setState { copy(oAuthState = OAuthState.OAuthSuccess) }
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