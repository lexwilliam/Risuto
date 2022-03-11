package com.lexwilliam.risuto.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.*
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.DetailMapper
import com.lexwilliam.risuto.mapper.HistoryMapper
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.util.getInitialAnimeDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeDetails: GetAnimeDetails,
    private val getAnimeCharacters: GetAnimeCharacters,
    private val insertAnimeHistory: InsertAnimeHistory,
    private val updateUserAnimeStatus: UpdateUserAnimeStatus,
    private val historyMapper: HistoryMapper,
    private val detailMapper: DetailMapper,
    savedState: SavedStateHandle
): BaseViewModel<AnimeContract.Event, AnimeContract.State, AnimeContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    override fun setInitialState(): AnimeContract.State {
        return AnimeContract.State(
            malId = -1,
            animeDetail = getInitialAnimeDetails(),
            characters = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: AnimeContract.Event) {
        when(event) {
            is AnimeContract.Event.InsertAnimeHistory -> insertAnimeHistory(event.anime)
            is AnimeContract.Event.UpdateUserAnimeStatus -> updateUserAnimeStatus(event.id, event.status, event.score)
        }
    }

    init {
        viewModelScope.launch(errorHandler) {
            malIdFromArgs?.let { id ->
                setState { copy(malId = id) }
                getAnimeDetails(id)
                getAnimeCharacters(id)
            }
            setState { copy(isLoading = false) }
        }
    }

    private fun getAnimeDetails(id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getAnimeDetails.execute(id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        animeDetail = anime,
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

    private fun getAnimeCharacters(id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getAnimeCharacters.execute(id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { characters ->
                                setState {
                                    copy(
                                        characters = characters.data
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun insertAnimeHistory(anime: AnimeDetailPresentation) {
        viewModelScope.launch(errorHandler) {
            insertAnimeHistory.execute(historyMapper.toDomain(anime))
        }
    }

    private fun updateUserAnimeStatus(id: Int, status: String, score: Int) {
        viewModelScope.launch(errorHandler) {
            updateUserAnimeStatus.execute(id, status, score).collect {
                Timber.d("changes: $status -> ${it.status} and $score -> ${it.score}")
                if(status == it.status && score == it.score) {
                    Timber.d("FAILS")
                }
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