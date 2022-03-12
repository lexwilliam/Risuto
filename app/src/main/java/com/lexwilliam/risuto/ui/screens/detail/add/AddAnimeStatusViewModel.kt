package com.lexwilliam.risuto.ui.screens.detail.add

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.UpdateUserAnimeStatus
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddAnimeStatusViewModel @Inject constructor(
    private val updateUserAnimeStatus: UpdateUserAnimeStatus
): BaseViewModel<AddAnimeStatusContract.Event, AddAnimeStatusContract.State, AddAnimeStatusContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    override fun setInitialState(): AddAnimeStatusContract.State {
        return AddAnimeStatusContract.State(
            status = getInitialMyListStatus(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: AddAnimeStatusContract.Event) {
        when(event) {
            is AddAnimeStatusContract.Event.UpdateUserAnimeStatus -> {
                updateUserAnimeStatus(event.id, event.numEpisodesWatched, event.status, event.score)
            }
        }
    }

    private fun updateUserAnimeStatus(
        id: Int,
        numEpisodesWatched: Int,
        status: String,
        score: Int
    ) {
        viewModelScope.launch(errorHandler) {
            updateUserAnimeStatus.execute(id, numEpisodesWatched, status, score)
        }
    }

    private fun getInitialMyListStatus() =
        AnimeDetailPresentation.MyListStatus(
            is_rewatching = false,
            num_episodes_watched = -1,
            score = -1,
            status = "",
            updated_at = ""
        )

}