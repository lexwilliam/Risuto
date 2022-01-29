package com.lexwilliam.risutov2.ui.profile

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.GetMyAnimeWithWatchStatus
import com.lexwilliam.domain.usecase.local.GetMyAnimes
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.MyAnimeMapper
import com.lexwilliam.risutov2.model.AnimePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val getMyAnimes: GetMyAnimes,
    private val getMyAnimeWithWatchStatus: GetMyAnimeWithWatchStatus,
    private val myAnimeMapper: MyAnimeMapper
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
            myAnimes = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: ProfileContract.Event) {
        TODO("Not yet implemented")
    }

    init {
        myAnimes()
    }

    private fun myAnimes() {
        viewModelScope.launch(errorHandler) {
            try {
                getMyAnimes.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { results ->
                        results.map { myAnimeMapper.toPresentation(it) }
                            .let { myAnimes ->
                                setState {
                                    copy(
                                        myAnimes = myAnimes
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

data class ProfileViewState(
    val myAnimeList: List<AnimePresentation> = emptyList()
)