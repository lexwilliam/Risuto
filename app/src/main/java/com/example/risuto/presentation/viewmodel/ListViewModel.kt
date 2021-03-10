package com.chun2maru.risutomvvm.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import com.example.risuto.domain.usecase.TopAnimeUseCase
import com.example.risuto.presentation.mapper.toGrid
import com.example.risuto.presentation.mapper.toRow
import com.example.risuto.presentation.model.TopAnimePresentation
import com.example.risuto.presentation.model.custom.GridStylePresentation
import com.example.risuto.presentation.model.custom.RowStylePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    fun onTopAiringAnime(type: String, page: Int, subType: String) {
        viewModelScope.launch {
            topAnimeUseCase.invoke(type, page, subType).collect { results ->
                val animes = results.map { anime -> anime.toGrid() }
                topAiringAnime = animes
            }
        }
    }
}
