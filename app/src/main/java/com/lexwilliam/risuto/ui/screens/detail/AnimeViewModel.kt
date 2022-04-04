package com.lexwilliam.risuto.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.*
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.DetailMapper
import com.lexwilliam.risuto.mapper.HistoryMapper
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.util.getInitialAnimeDetails
import com.lexwilliam.risuto.util.getInitialAnimeVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeDetails: GetAnimeDetails,
    private val getAnimeCharacters: GetAnimeCharacters,
    private val getAnimeVideos: GetAnimeVideos,
    private val insertAnimeHistory: InsertAnimeHistory,
    private val updateUserAnimeStatus: UpdateUserAnimeStatus,
    private val deleteUserAnimeStatus: DeleteUserAnimeStatus,
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
            myListStatus = AnimeDetailPresentation.MyListStatus(false, -1, -1, "", ""),
            characters = emptyList(),
            videos = getInitialAnimeVideos(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: AnimeContract.Event) {
        when(event) {
            is AnimeContract.Event.InsertAnimeHistory -> insertAnimeHistory(event.anime)
            is AnimeContract.Event.UpdateUserAnimeStatus -> {
                updateUserAnimeStatus(event.id, event.numEpisodesWatched, event.status, event.score)
                setState {
                    copy(
                        myListStatus = AnimeDetailPresentation.MyListStatus(false, event.numEpisodesWatched, event.score, event.status, "")
                    )
                }
            }
            is AnimeContract.Event.DeleteUserAnimeStatus -> {
                deleteUserAnimeStatus(event.id)
                setState {
                    copy(
                        myListStatus = AnimeDetailPresentation.MyListStatus(false, -1, -1, "", "")
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch(errorHandler) {
            malIdFromArgs?.let { id ->
                getAnimeDetails(id)
                setState { copy(malId = id) }
                getAnimeCharacters(id)
                getAnimeVideos(id)
            }
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
                                        myListStatus = anime.my_list_status,
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

    private fun getAnimeVideos(id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getAnimeVideos.execute(id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { videos ->
                                setState {
                                    copy(
                                        videos = videos
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

    private fun updateUserAnimeStatus(id: Int, numEpisodesWatched: Int, status: String, score: Int) {
        viewModelScope.launch(errorHandler) {
            updateUserAnimeStatus.execute(id, numEpisodesWatched, status, score).collect()
        }
    }

    private fun deleteUserAnimeStatus(id: Int) {
        viewModelScope.launch(errorHandler) {
            deleteUserAnimeStatus.execute(id)
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