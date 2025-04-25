package com.lexwilliam.risuto.ui.screens.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetAccessTokenFromCache
import com.lexwilliam.domain.usecase.GetUserAnimeList
import com.lexwilliam.domain.usecase.GetUserProfile
import com.lexwilliam.domain.usecase.Logout
import com.lexwilliam.risuto.ui.base.BaseViewModel
import com.lexwilliam.risuto.mapper.UserMapper
import com.lexwilliam.risuto.util.getInitialStateUserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfile: GetUserProfile,
    private val userMapper: UserMapper,
    private val getAccessTokenFromCache: GetAccessTokenFromCache,
    private val logout: Logout,
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
            userProfile = getInitialStateUserProfile().data,
            isLoading = true,
            isError = false,
            isGuest = false,
            isLoggedOut = false,
        )
    }

    override fun handleEvents(event: ProfileContract.Event) {
        when (event) {
            ProfileContract.Event.Logout -> handleLogout()
            ProfileContract.Event.NavigationDone -> {
                setState { copy(isLoggedOut = false) }
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
        }
    }

    private fun handleLogout() {
        viewModelScope.launch(errorHandler) {
            logout.execute()
            setState { copy(isLoading = false, isLoggedOut = true) }
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
                                        userProfile = profile.data,
                                        isLoading = false
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
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