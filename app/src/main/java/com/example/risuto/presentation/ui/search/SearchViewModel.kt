package com.example.risuto.presentation.ui.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toRow
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.home.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject constructor(
        private val searchAnimeUseCase: SearchAnimeUseCase,
        private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var searchAnimes = MutableStateFlow<List<AnimeListPresentation>>(listOf())
    private var query = MutableStateFlow("")

    private var _state = MutableStateFlow(SearchViewState())
    val state = _state.asStateFlow()

    fun onSearchAnime(animeName: String) {
        viewModelScope.launch {
            searchAnimeUseCase.invoke(animeName).collect { results ->
                val animes = results.map { anime -> anime.toRow() }
                searchAnimes.value = animes
            }
            combine(
                query,
                searchAnimes
            ) { query, searchAnime ->
                SearchViewState(
                    query = query,
                    searchAnimes = searchAnime
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
            searchAnimes.value.forEach { anime ->
                Log.d("TAG", anime.title)
            }
        }
    }

    fun onQueryChange(query: String){
        this.query.value = query
    }
}

data class SearchViewState(
    val query: String = "",
    val searchAnimes: List<AnimeListPresentation> = emptyList()
)