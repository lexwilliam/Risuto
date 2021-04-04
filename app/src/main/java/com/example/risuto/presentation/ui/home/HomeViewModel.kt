package com.example.risuto.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.domain.usecase.SeasonAnimeUseCase
import com.example.risuto.domain.usecase.TopAnimeUseCase
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.AnimeSeasonListPresentation
import com.example.risuto.presentation.util.Error
import com.example.risuto.presentation.util.ExceptionHandler
import com.example.risuto.presentation.util.getCurrentYear
import com.example.risuto.presentation.util.thisSeason
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val seasonAnimeUseCase: SeasonAnimeUseCase,
    private val topAnimeUseCase: TopAnimeUseCase,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onError(message)
    }

    private var homeJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        homeJob?.cancel()
    }

    private val currentSeasonAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())
    private val topAiringAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())
    private val topAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())
    private val topUpcomingAnime = MutableStateFlow<List<AnimeListPresentation>>(listOf())

    private val _state = MutableStateFlow(HomeViewState(isLoading = false, error = null))
    val state = _state.asStateFlow()

    init {
        onLoading()
        homeJob?.cancel()
        homeJob = launchCoroutine {
            refresh()
            combine(
                currentSeasonAnime,
                topAiringAnime,
                topAnime,
                topUpcomingAnime
            ) { currentSeasonAnime, topAiringAnime, topAnime, topUpcoming ->
                HomeViewState(
                    currentSeasonAnime = currentSeasonAnime,
                    topAiringAnime = topAiringAnime,
                    topAnime = topAnime,
                    topUpcomingAnime = topUpcoming,
                    isLoading = false,
                    error = null
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    private fun refresh() {
        onCurrentSeasonAnime()
        onTopAiringAnime()
        onTopAnime()
        onTopUpcomingAnime()
    }

    private fun onCurrentSeasonAnime() {
        viewModelScope.launch {
            seasonAnimeUseCase.invoke(getCurrentYear(), thisSeason).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                currentSeasonAnime.value = animes
            }
        }
    }

    private fun onTopAiringAnime() {
        viewModelScope.launch {
            topAnimeUseCase.invoke(1, "airing").collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                topAiringAnime.value = animes
            }
        }
    }

    private fun onTopUpcomingAnime() {
        viewModelScope.launch {
            topAnimeUseCase.invoke(1, "upcoming").collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                topUpcomingAnime.value = animes
            }
        }
    }

    private fun onTopAnime() {
        viewModelScope.launch {
            topAnimeUseCase.invoke(1, "tv").collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                topAnime.value = animes
            }
        }
    }

    private fun onLoading() {
        _state.value = _state.value.copy(isLoading = true)
    }

    private fun onError(message: Int){
        _state.value = _state.value.copy(error = Error(message))
    }
}

data class HomeViewState (
    val currentSeasonAnime: List<AnimeListPresentation> = emptyList(),
    val topAnime: List<AnimeListPresentation> = emptyList(),
    val topAiringAnime: List<AnimeListPresentation> = emptyList(),
    val topUpcomingAnime: List<AnimeListPresentation> = emptyList(),
    val isLoading: Boolean,
    val error: Error?
)
