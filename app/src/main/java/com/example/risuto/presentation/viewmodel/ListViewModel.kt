package com.chun2maru.risutomvvm.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toGrid
import com.chun2maru.risutomvvm.presentation.mapper.toRow
import com.example.risuto.domain.usecase.TopAnimeUseCase
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.model.RowStylePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject constructor(
    private val searchAnimeUseCase: SearchAnimeUseCase,
    private val topAnimeUseCase: TopAnimeUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var searchAnime by mutableStateOf(listOf<RowStylePresentation>())

    var topAiringAnime by mutableStateOf(listOf<GridStylePresentation>())

    fun onSearchAnime(animeName: String) {
        viewModelScope.launch {
            searchAnimeUseCase.invoke(animeName).collect { results ->
                val animes = results.map { anime -> anime.toRow() }
                searchAnime = animes
            }
        }
    }

    fun onTopAiringAnime(page: Int, subType: String) {
        viewModelScope.launch {
            topAnimeUseCase.invoke(page, subType).collect { results ->
                val animes = results.map { anime -> anime.toGrid() }
                topAiringAnime = animes
            }
        }
    }
}
