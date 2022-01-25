package com.lexwilliam.risutov2.ui.season

import android.util.Log
import com.example.risuto.data.remote.model.detail.Archive
import com.example.risuto.domain.usecase.remote.GetSeasonArchiveUseCase
import com.example.risuto.domain.usecase.remote.SeasonAnimeUseCase
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.model.AnimeListPresentation
import com.example.risuto.presentation.util.*
import com.lexwilliam.risutov2.util.ExceptionHandler
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

    private var season = MutableStateFlow(_root_ide_package_.com.lexwilliam.risutov2.util.getCurrentSeason())
    private var year = MutableStateFlow(_root_ide_package_.com.lexwilliam.risutov2.util.getCurrentYear())

    private var _state = MutableStateFlow(SeasonViewState())
    val state = _state.asStateFlow()

    init {
        seasonJob?.cancel()
        seasonJob = launchCoroutine {
            seasonAnimeUseCase.invoke(year.value, season.value).collect { results ->
                val animes = results.map { anime -> com.lexwilliam.risutov2.mapper.toPresentation() }
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
                val animes = results.map { anime -> com.lexwilliam.risutov2.mapper.toPresentation() }
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
    val season: String = _root_ide_package_.com.lexwilliam.risutov2.util.getCurrentSeason(),
    val year: Int = _root_ide_package_.com.lexwilliam.risutov2.util.getCurrentYear(),
    val seasonArchive: List<Archive> = emptyList(),
    val seasonAnimes: List<AnimeListPresentation> = emptyList()
)