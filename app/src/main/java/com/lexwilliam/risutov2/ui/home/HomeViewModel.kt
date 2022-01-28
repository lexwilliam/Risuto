package com.lexwilliam.risutov2.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.remote.GetCurrentSeasonAnime
import com.lexwilliam.domain.usecase.remote.GetSeasonAnime
import com.lexwilliam.domain.usecase.remote.GetTopAnime
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.AnimeMapper
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.util.Error
import com.lexwilliam.risutov2.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getCurrentSeasonAnime: GetCurrentSeasonAnime,
    private val topAnimeUseCase: GetTopAnime,
    private val animeMapper: AnimeMapper
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

    private val currentSeasonAnime = MutableStateFlow<List<AnimePresentation>>(listOf())
    private val topAiringAnime = MutableStateFlow<List<AnimePresentation>>(listOf())
    private val topAnime = MutableStateFlow<List<AnimePresentation>>(listOf())
    private val topUpcomingAnime = MutableStateFlow<List<AnimePresentation>>(listOf())

    private val _state = MutableStateFlow(HomeViewState(isLoading = false, error = null))
    val state = _state.asStateFlow()

    init {
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
        viewModelScope.launch {
            onCurrentSeasonAnime()
            onTopAiringAnime()
            onTopAnime()
            onTopUpcomingAnime()
        }
    }

    private fun onCurrentSeasonAnime() {
        viewModelScope.launch {
            getCurrentSeasonAnime.execute().collect { results ->
                val animes = results.anime.map { anime -> animeMapper.toPresentation(anime) }
                currentSeasonAnime.value = animes
            }
        }
    }

    private fun onTopAiringAnime() {
        viewModelScope.launch {
            topAnimeUseCase.execute(1, "airing").collect { results ->
                val animes = results.top.map { anime -> animeMapper.toPresentation(anime) }
                topAiringAnime.value = animes
            }
        }
    }

    private fun onTopUpcomingAnime() {
        viewModelScope.launch {
            topAnimeUseCase.execute(1, "upcoming").collect { results ->
                val animes = results.top.map { anime -> animeMapper.toPresentation(anime) }
                topUpcomingAnime.value = animes
            }
        }
    }

    private fun onTopAnime() {
        viewModelScope.launch {
            topAnimeUseCase.execute(1, "tv").collect { results ->
                val animes = results.top.map { anime -> animeMapper.toPresentation(anime) }
                topAnime.value = animes
            }
        }
    }

    private fun onError(message: Int){
        _state.value = _state.value.copy(error = Error(message))
    }
}

data class HomeViewState (
    val currentSeasonAnime: List<AnimePresentation> = emptyList(),
    val topAnime: List<AnimePresentation> = emptyList(),
    val topAiringAnime: List<AnimePresentation> = emptyList(),
    val topUpcomingAnime: List<AnimePresentation> = emptyList(),
    val isLoading: Boolean,
    val error: Error?
)
