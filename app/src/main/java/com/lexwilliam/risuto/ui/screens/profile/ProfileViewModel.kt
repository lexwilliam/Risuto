package com.lexwilliam.risuto.ui.screens.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.model.remote.user.UserProfile
import com.lexwilliam.domain.usecase.GetUserProfile
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.UserMapper
import com.lexwilliam.risuto.util.getInitialStateUserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfile: GetUserProfile,
    private val userMapper: UserMapper
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
            userProfile = getInitialStateUserProfile(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: ProfileContract.Event) {}

    init {
        getUserProfile()
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
                                        userProfile = profile,
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