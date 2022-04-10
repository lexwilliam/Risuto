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

    override fun setInitialState(): SearchContract.State {
        return SearchContract.State(
            q = null,
            type = null,
            score = null,
            minScore = null,
            maxScore = null,
            status = null,
            rating = null,
            sfw = null,
            genres = null,
            genresExclude = null,
            orderBy = null,
            sort = null,
            letter = null,
            producer = null,
            resultType = ResultType.History,
            searchAnimes = emptyList(),
            searchAnimesPaging = null,
            animeHistory = emptyList(),
            searchHistory = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: SearchContract.Event) {
        when(event) {
            is SearchContract.Event.OnResultChanged -> {
                setState { copy(resultType = event.resultType) }
            }

            is SearchContract.Event.OnQueryChanged -> {
                setState {
                    copy(
                        q = event.q,
                        type = event.type,
                        score = event.score,
                        minScore = event.minScore,
                        maxScore = event.maxScore,
                        status = event.status,
                        rating = event.rating,
                        sfw = event.sfw,
                        genres = event.genres,
                        genresExclude = event.genresExclude,
                        orderBy = event.orderBy,
                        sort = event.sort,
                        letter = event.letter,
                        producer = event.producer
                    )
                }
            }

            is SearchContract.Event.SearchAnime -> {
                getSearchAnime(
                    q = viewState.value.q,
                    type = viewState.value.type,
                    score = viewState.value.score,
                    minScore = viewState.value.minScore,
                    maxScore = viewState.value.maxScore,
                    status = viewState.value.status,
                    rating = viewState.value.rating,
                    sfw = viewState.value.sfw,
                    genres = viewState.value.genres,
                    genresExclude = viewState.value.genresExclude,
                    orderBy = viewState.value.orderBy,
                    sort = viewState.value.sort,
                    letter = viewState.value.letter,
                    producer = viewState.value.producer
                )
            }

            is SearchContract.Event.SearchAnimePaging -> {
                setState {
                    copy(
                        searchAnimesPaging = getSearchAnimePaging(
                            q = viewState.value.q,
                            type = viewState.value.type,
                            score = viewState.value.score,
                            minScore = viewState.value.minScore,
                            maxScore = viewState.value.maxScore,
                            status = viewState.value.status,
                            rating = viewState.value.rating,
                            sfw = viewState.value.sfw,
                            genres = viewState.value.genres,
                            genresExclude = viewState.value.genresExclude,
                            orderBy = viewState.value.orderBy,
                            sort = viewState.value.sort,
                            letter = viewState.value.letter,
                            producer = viewState.value.producer
                        )
                    )
                }
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
                            searchAnimesPaging = getSearchAnimePaging(
                                q = viewState.value.q,
                                type = viewState.value.type,
                                score = viewState.value.score,
                                minScore = viewState.value.minScore,
                                maxScore = viewState.value.maxScore,
                                status = viewState.value.status,
                                rating = viewState.value.rating,
                                sfw = viewState.value.sfw,
                                genres = viewState.value.genres,
                                genresExclude = viewState.value.genresExclude,
                                orderBy = viewState.value.orderBy,
                                sort = viewState.value.sort,
                                letter = viewState.value.letter,
                                producer = viewState.value.producer
                            )
                        )
                    }
                    delay(1000)
                    setState { copy(isRefreshing = false) }
                }
            }
        }
    }

    private val genreFromArgs = savedStateHandle.get<Int>("genre")
    private val producerFromArgs = savedStateHandle.get<Int>("producer")

    init {
        genreFromArgs.let {
            if(it != null && it != -1) {
                setState {
                    copy(
                        genres = it.toString(),
                        orderBy = "rank",
                        sort = "desc",
                        isLoading = false,
                        resultType = ResultType.FullResult
                    )
                }
            }
        }
        producerFromArgs.let {
            Timber.d(it.toString())
            if(it != null && it != -1) {
                setState {
                    copy(
                        producer = it.toString(),
                        orderBy = "start_date",
                        sort = "desc",
                        isLoading = false,
                        resultType = ResultType.FullResult
                    )
                }
            }
        }
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
        Timber.d(genres)
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
        Timber.d(genres)
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
                                        animeHistory = animes,
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
                                        searchHistory = searchHistory,
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