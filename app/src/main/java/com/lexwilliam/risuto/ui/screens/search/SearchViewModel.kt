package com.lexwilliam.risuto.ui.screens.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.lexwilliam.domain.usecase.DeleteAllAnimeHistory
import com.lexwilliam.domain.usecase.*
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.mapper.HistoryMapper
import com.lexwilliam.risuto.model.SearchHistoryPresentation
import com.lexwilliam.risuto.model.AnimePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class  SearchViewModel @Inject constructor(
    private val getSearchAnime: GetSearchAnime,
    private val getSearchAnimePaging: GetSearchAnimePaging,
    private val getAllSearchHistory: GetSearchHistory,
    private val getAllAnimeHistory: GetAnimeHistory,
    private val insertSearchHistory: InsertSearchHistory,
    private val deleteSearchHistory: DeleteSearchHistory,
    private val deleteAllSearchHistory: DeleteAllSearchHistory,
    private val deleteAnimeHistory: DeleteAnimeByTitle,
    private val deleteAllAnimeHistory: DeleteAllAnimeHistory,
    private val animeMapper: AnimeMapper,
    private val historyMapper: HistoryMapper,
    savedStateHandle: SavedStateHandle
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

    private val genreFromArgs = savedStateHandle.get<Int>("genre")

    override fun setInitialState(): SearchContract.State {
        return SearchContract.State(
            searchAnimes = emptyList(),
            searchAnimesPaging = null,
            animeHistory = emptyList(),
            searchHistory = emptyList(),
            genreFromArgs = "",
            isLoading = true,
            isError = false
        )
    }

    init {
        genreFromArgs.let {
            if(genreFromArgs != null) {
                setState {
                    copy(genreFromArgs = it.toString())
                }
            }
        }
    }

    override fun handleEvents(event: SearchContract.Event) {
        when(event) {
            is SearchContract.Event.SearchAnimePaging ->
                setState {
                    copy(searchAnimesPaging = getSearchAnimePaging(event.q, event.type, event.score, event.minScore, event.maxScore, event.status, event.rating, event.sfw, event.genres, event.genresExclude, event.orderBy, event.sort, event.letter, event.producer))
                }

            is SearchContract.Event.SearchAnime ->
                if(event.q?.length!! > 3) {
                    getSearchAnime(event.q, event.type, event.score, event.minScore, event.maxScore, event.status, event.rating, event.sfw, event.genres, event.genresExclude, event.orderBy, event.sort, event.letter, event.producer)
                }

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

            is SearchContract.Event.RefreshPaging -> {
                viewModelScope.launch(errorHandler) {
                    setState {
                        copy(
                            isRefreshing = true,
                            searchAnimesPaging = getSearchAnimePaging(event.q, event.type, event.score, event.minScore, event.maxScore, event.status, event.rating, event.sfw, event.genres, event.genresExclude, event.orderBy, event.sort, event.letter, event.producer)
                        )
                    }
                    delay(1000)
                    setState { copy(isRefreshing = false) }
                }
            }
        }
    }

    init {
        animeHistory()
        searchHistory()
    }

    private fun getSearchAnime(
        q: String?,
        type: String?,
        score: Double?,
        minScore: Double?,
        maxScore: Double?,
        status: String?,
        rating: String?,
        sfw: Boolean?,
        genres: String?,
        genresExclude: String?,
        orderBy: String?,
        sort: String?,
        letter: String?,
        producer: String?
    ) {
        viewModelScope.launch(errorHandler) {
            try {
                setState {
                    copy(
                        searchAnimes = emptyList()
                    )
                }
                getSearchAnime.execute(1, 6, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        animeMapper.toPresentation(it)
                            .let { anime ->
                                setState {
                                    copy(
                                        searchAnimes = anime.data,
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun getSearchAnimePaging(
        q: String?,
        type: String?,
        score: Double?,
        minScore: Double?,
        maxScore: Double?,
        status: String?,
        rating: String?,
        sfw: Boolean?,
        genres: String?,
        genresExclude: String?,
        orderBy: String?,
        sort: String?,
        letter: String?,
        producer: String?
    ): Flow<PagingData<AnimePresentation.Data>> {
        return getSearchAnimePaging.execute(q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
            .map { it.map { animeMapper.toPresentation(it) } }
            .cachedIn(viewModelScope)

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