package com.lexwilliam.risutov2.ui.search

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.*
import com.lexwilliam.domain.usecase.remote.GetSearchAnime
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.AnimeMapper
import com.lexwilliam.risutov2.mapper.HistoryMapper
import com.lexwilliam.risutov2.model.local.SearchHistoryPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchAnime: GetSearchAnime,
    private val getAllSearchHistory: GetSearchHistory,
    private val getAllAnimeHistory: GetAnimeHistory,
    private val insertSearchHistory: InsertSearchHistory,
    private val deleteSearchHistory: DeleteSearchHistory,
    private val deleteAllSearchHistory: DeleteAllSearchHistory,
    private val deleteAnimeHistory: DeleteAnimeByTitle,
    private val deleteAllAnimeHistory: DeleteAllAnimeHistory,
    private val animeMapper: AnimeMapper,
    private val historyMapper: HistoryMapper
): BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private var searchJob: Job? = null

    override fun setInitialState(): SearchContract.State {
        return SearchContract.State(
            searchAnimes = emptyList(),
            animeHistory = emptyList(),
            searchHistory = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: SearchContract.Event) {
        when(event) {
            is SearchContract.Event.SearchAnime ->
                onSearchAnime(event.q, event.type, event.status, event.genre, event.limit, event.orderBy, event.sort, event.page)

            is SearchContract.Event.InsertSearchHistory ->
                insertSearchHistory(event.query)

            is SearchContract.Event.DeleteAllAnimeHistory ->
                deleteAllAnimeHistory()

            is SearchContract.Event.DeleteAllSearchHistory ->
                deleteAllSearchHistory()

            is SearchContract.Event.DeleteAnimeHistoryByTitle ->
                deleteAnimeHistory(event.title)

            is SearchContract.Event.DeleteSearchHistory ->
                deleteSearchHistory(event.query)
        }
    }

    init {
        animeHistory()
        searchHistory()
    }

    private fun onSearchAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        limit: Int?,
        orderBy: String?,
        sort: String?,
        page: Int?
    ) {
        if(q?.length!! > 3) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch(errorHandler) {
                try {
                    setState {
                        copy(
                            searchAnimes = emptyList()
                        )
                    }
                    getSearchAnime.execute(q, type, status, genre, limit, orderBy, sort, page)
                        .catch { throwable ->
                            handleExceptions(throwable)
                        }
                        .collect {
                            animeMapper.toPresentation(it)
                                .let { anime ->
                                    setState {
                                        copy(
                                            searchAnimes = anime.anime
                                        )
                                    }
                                }
                        }
                } catch (throwable: Throwable) {
                    handleExceptions(throwable)
                }
            }
        }
    }

    private fun animeHistory() {
        viewModelScope.launch(errorHandler) {
            try {
                getAllAnimeHistory.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { results ->
                        results.map { historyMapper.toPresentation(it) }
                            .let { animes ->
                                setState {
                                    copy(
                                        animeHistory = animes
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun searchHistory() {
        viewModelScope.launch(errorHandler) {
            try {
                getAllSearchHistory.execute()
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { results ->
                        results.map { historyMapper.toPresentation(it) }
                            .let { searchHistory ->
                                setState {
                                    copy(
                                        searchHistory = searchHistory
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun insertSearchHistory(query: String) {
        viewModelScope.launch(errorHandler) {
            insertSearchHistory.execute(historyMapper.toDomain(SearchHistoryPresentation(query = query)))
        }
    }

    private fun deleteAllSearchHistory() {
        viewModelScope.launch(errorHandler) {
            deleteAllSearchHistory.execute()
        }
    }

    private fun deleteSearchHistory(query: String) {
        viewModelScope.launch(errorHandler) {
            deleteSearchHistory.execute(query)
        }
    }

    private fun deleteAllAnimeHistory() {
        viewModelScope.launch(errorHandler) {
            deleteAllAnimeHistory.execute()
        }
    }

    private fun deleteAnimeHistory(title: String) {
        viewModelScope.launch(errorHandler) {
            deleteAnimeHistory.execute(title)
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