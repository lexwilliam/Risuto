package com.example.risuto.presentation.ui.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.risuto.domain.usecase.remote.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.data.local.Results
import com.example.risuto.domain.usecase.local.*
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.mapper.toDomain
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.model.SearchHistoryPresentation
import com.example.risuto.presentation.util.Error
import com.example.risuto.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject constructor(
        private val searchAnimeUseCase: SearchAnimeUseCase,
        private val getAllSearchHistoryUseCase: GetAllSearchHistoryUseCase,
        private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
        private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase,
        private val getAllAnimeHistoryUseCase: GetAllAnimeHistoryUseCase,
        private val deleteAnimeHistoryUseCase: DeleteAnimeHistoryUseCase,
        private val savedStateHandle: SavedStateHandle,
    ): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onSearchError(message)
    }

    private var searchJob: Job? = null
    private var getAllSearchJob: Job? = null
    private var insertSearchJob: Job? = null
    private var deleteSearchJob: Job? = null
    private var getAllAnimeJob: Job? = null
    private var deleteAnimeJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    init {
        loadHistory()
    }

    private var query = MutableStateFlow(QuerySearch())

    private var _state = MutableStateFlow(SearchViewState(query = QuerySearch(), error = null, isLoading = false))
    val state = _state.asStateFlow()

    fun onSearchAnime() {
        searchJob?.cancel()
        searchJob = launchCoroutine {
            onSearchLoading()
            _state.value = _state.value.copy(query = query.value)
            searchAnimeUseCase.invoke(query.value).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                onSearchComplete(animes)
            }
        }
    }

    fun getQuery(q: QuerySearch) {
        query.value = query.value.copy(q = q.q)
        query.value = query.value.copy(limit = q.limit)
    }

    private fun onSearchComplete(animes: List<AnimeListPresentation>) {
        _state.value = _state.value.copy(searchAnimes = animes, error = null, isLoading = false)
    }

    private fun onSearchLoading() {
        _state.value = _state.value.copy(searchAnimes = emptyList(), isLoading = true)
    }

    private fun onSearchError(message: Int){
        _state.value = _state.value.copy(searchAnimes = emptyList(), error = Error(message), isLoading = false)
    }

    private fun loadHistory() {
        getAllSearchJob = launchCoroutine {
            getAllSearchHistoryUseCase.invoke().collect { results ->
                val history = results.map { it.toPresentation() }
                _state.value = _state.value.copy(searchHistory = history)
            }
        }
        getAllAnimeJob = launchCoroutine {
            getAllAnimeHistoryUseCase.invoke().collect { results ->
                val history = results.map { it.toPresentation() }
                _state.value = _state.value.copy(animeHistory = history)
            }
        }
    }

    fun insertSearchHistory(query: SearchHistoryPresentation) {
        insertSearchJob = launchCoroutine {
            insertSearchHistoryUseCase.invoke(query.toDomain()).collect { result ->
                if(result == Results.SUCCESS) {
                    Log.d("TAG", "Saving Success")
                } else {
                    Log.d("TAG", "Saving Failed")
                }
            }
        }
    }

    fun deleteSearchHistory(query: String) {
        deleteSearchJob = launchCoroutine {
            deleteSearchHistoryUseCase.invoke(query)
        }
    }

    fun deleteAnimeHistory(title: String) {
        deleteAnimeJob = launchCoroutine {
            deleteAnimeHistoryUseCase.invoke(title)
        }
    }
}

data class SearchViewState(
    val query: QuerySearch,
    val searchAnimes: List<AnimeListPresentation> = emptyList(),
    val animeHistory: List<AnimeListPresentation> = emptyList(),
    val searchHistory: List<SearchHistoryPresentation> = emptyList(),
    val error: Error?,
    val isLoading: Boolean
)