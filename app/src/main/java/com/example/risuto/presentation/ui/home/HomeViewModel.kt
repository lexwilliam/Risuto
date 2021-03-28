package com.example.risuto.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.presentation.mapper.toGrid
import com.example.risuto.domain.usecase.TopAnimeUseCase
import com.example.risuto.presentation.model.GridStylePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val topAnimeUseCase: TopAnimeUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val topAiringAnime = MutableStateFlow<List<GridStylePresentation>>(listOf())
    private val topAnime = MutableStateFlow<List<GridStylePresentation>>(listOf())
    private val topUpcomingAnime = MutableStateFlow<List<GridStylePresentation>>(listOf())

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
    val topAnime: List<GridStylePresentation> = emptyList(),
    val topAiringAnime: List<GridStylePresentation> = emptyList(),
    val topUpcomingAnime: List<GridStylePresentation> = emptyList()
)
