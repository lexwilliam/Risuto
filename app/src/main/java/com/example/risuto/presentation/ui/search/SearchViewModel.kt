package com.example.risuto.presentation.ui.search

import androidx.lifecycle.SavedStateHandle
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
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
        private val savedStateHandle: SavedStateHandle,
    ): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onSearchError(message)
    }

    private var searchJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
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

    fun getQuery(q: String) {
        query.value = query.value.copy(q = q)
    }

    fun getGenre(genre: Int) {
        query.value = query.value.copy(genre = genre)
    }

    fun getStatus(status: String) {
        query.value = query.value.copy(status = status)
    }

    fun getType(type: String) {
        query.value = query.value.copy(type = type)
    }

    fun getOrderBy(order: String) {
        query.value = query.value.copy(order_by = order)
    }

    fun getSort(sort: String) {
        query.value = query.value.copy(sort = sort)
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
}

data class SearchViewState(
    val query: QuerySearch,
    val searchAnimes: List<AnimeListPresentation> = emptyList(),
    val error: Error?,
    val isLoading: Boolean
)