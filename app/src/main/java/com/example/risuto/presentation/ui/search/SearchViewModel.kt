package com.example.risuto.presentation.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toRow
import com.example.risuto.presentation.model.RowStylePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject constructor(
        private val searchAnimeUseCase: SearchAnimeUseCase,
        private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var searchAnime by mutableStateOf(listOf<RowStylePresentation>())

    fun onSearchAnime(animeName: String) {
        viewModelScope.launch {
            searchAnimeUseCase.invoke(animeName).collect { results ->
                val animes = results.map { anime -> anime.toRow() }
                searchAnime = animes
            }
        }
    }
}