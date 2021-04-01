package com.example.risuto.presentation.ui.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toGrid
import com.chun2maru.risutomvvm.presentation.mapper.toRow
import com.example.risuto.domain.usecase.TopAnimeUseCase
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.search.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val topAnimeUseCase: TopAnimeUseCase,
    private val searchAnimeUseCase: SearchAnimeUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val topAiringAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())
    private val topAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())
    private val topUpcomingAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            refresh()
            combine(
                topAiringAnime,
                topAnime,
                topUpcomingAnime
            ) { topAiringAnime, topAnime, topUpcoming ->
                HomeViewState(
                    topAiringAnime = topAiringAnime,
                    topAnime = topAnime,
                    topUpcomingAnime = topUpcoming
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    private fun refresh() {
        onTopAiringAnime()
        onTopAnime()
        onTopUpcomingAnime()
    }

    private fun onTopAiringAnime() {
        viewModelScope.launch {
            topAnimeUseCase.invoke(1, "airing").collect { results ->
                val animes = results.map { anime -> anime.toGrid() }
                topAiringAnime.value = animes
            }
        }
    }

    private fun onTopUpcomingAnime() {
        viewModelScope.launch {
            topAnimeUseCase.invoke(1, "upcoming").collect { results ->
                val animes = results.map { anime -> anime.toGrid() }
                topUpcomingAnime.value = animes
            }
        }
    }

    private fun onTopAnime() {
        viewModelScope.launch {
            topAnimeUseCase.invoke(1, "tv").collect { results ->
                val animes = results.map { anime -> anime.toGrid() }
                topAnime.value = animes
            }
        }
    }
}

data class HomeViewState (
    val topAnime: List<AnimeListPresentation> = emptyList(),
    val topAiringAnime: List<AnimeListPresentation> = emptyList(),
    val topUpcomingAnime: List<AnimeListPresentation> = emptyList()
)
