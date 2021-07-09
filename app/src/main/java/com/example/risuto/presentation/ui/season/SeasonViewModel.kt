package com.example.risuto.presentation.ui.season

import android.util.Log
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.data.remote.model.detail.Archive
import com.example.risuto.domain.usecase.remote.GetSeasonArchiveUseCase
import com.example.risuto.domain.usecase.remote.SeasonAnimeUseCase
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.util.*
import com.example.risuto.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import java.util.*
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

    private var season = MutableStateFlow(getCurrentSeason())
    private var year = MutableStateFlow(getCurrentYear())

    private var _state = MutableStateFlow(SeasonViewState())
    val state = _state.asStateFlow()

    init {
        seasonJob?.cancel()
        seasonJob = launchCoroutine {
            seasonAnimeUseCase.invoke(year.value, season.value).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                _state.value = _state.value.copy(seasonAnimes = animes, year = year.value, season = season.value)
            }
            seasonArchiveUseCase.invoke().collect { result ->
                Log.d("TAG", "TESTVIEWMODELARCHIVE")
                val archive = result.toPresentation().archive
                _state.value = _state.value.copy(seasonArchive = archive)
            }
        }
    }

    private fun onSeasonAnime() {
        seasonJob?.cancel()
        seasonJob = launchCoroutine {
            seasonAnimeUseCase.invoke(year.value, season.value).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                _state.value = _state.value.copy(seasonAnimes = animes, year = year.value, season = season.value)
            }
        }
    }

    fun setSeason(str: String) {
        val strToSeason = str.split(" ")
        season.value = strToSeason.first().decapitalize(Locale.ROOT)
        year.value = strToSeason.last().toInt()
        onSeasonAnime()
    }
}

data class SeasonViewState(
    val season: String = getCurrentSeason(),
    val year: Int = getCurrentYear(),
    val seasonArchive: List<Archive> = emptyList(),
    val seasonAnimes: List<AnimeListPresentation> = emptyList()
)