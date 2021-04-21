package com.example.risuto.presentation.ui.season

import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.data.remote.model.detail.Archive
import com.example.risuto.domain.usecase.GetSeasonArchiveUseCase
import com.example.risuto.domain.usecase.SeasonAnimeUseCase
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.util.ExceptionHandler
import com.example.risuto.presentation.util.getCurrentMonth
import com.example.risuto.presentation.util.getCurrentYear
import com.example.risuto.presentation.util.thisSeason
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel
    @Inject constructor(
        private val seasonAnimeUseCase: SeasonAnimeUseCase,
        private val seasonArchiveUseCase: GetSeasonArchiveUseCase
    ): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var seasonJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        seasonJob?.cancel()
    }

    private var _state = MutableStateFlow(SeasonViewState())
    val state = _state.asStateFlow()

    init {
        seasonJob?.cancel()
        seasonJob = launchCoroutine {
            seasonArchiveUseCase.invoke().collect { result ->
                _state.value = _state.value.copy(seasonArchive = result.archive)
            }
        }
    }

    fun getSeasonAnime(year: Int, season: String) {
        seasonJob?.cancel()
        seasonJob = launchCoroutine {
            seasonAnimeUseCase.invoke(getCurrentYear(), thisSeason).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                _state.value = _state.value.copy(seasonAnimes = animes)
            }
        }
    }
}

data class SeasonViewState(
    val seasonArchive: List<Archive> = emptyList(),
    val seasonAnimes: List<AnimeListPresentation> = emptyList()
)