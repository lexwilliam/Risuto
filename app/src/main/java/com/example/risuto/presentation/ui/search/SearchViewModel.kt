package com.example.risuto.presentation.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toRow
import com.example.risuto.presentation.model.RowStylePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject constructor(
        private val searchAnimeUseCase: SearchAnimeUseCase,
        private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var query = MutableStateFlow("")
    private var searchAnimes = MutableStateFlow<List<RowStylePresentation>>(listOf())

    private var _state = MutableStateFlow(SearchViewState())
    val state = _state.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            combine(
                query,
                searchAnimes
            ) { query, searchAnimes ->
                SearchViewState(
                    query = query,
                    searchAnimes = searchAnimes
                )
            }
        }
    }

    private fun onSearchAnime(animeName: String) {
        viewModelScope.launch {
            searchAnimeUseCase.invoke(animeName).collect { results ->
                val animes = results.map { anime -> anime.toRow() }
                searchAnimes.value = animes
            }
        }
    }
}

data class SearchViewState(
    val query: String = "",
    val searchAnimes: List<RowStylePresentation> = emptyList()
)