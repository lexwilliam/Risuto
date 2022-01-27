package com.lexwilliam.risutov2.ui.season

import com.lexwilliam.domain.usecase.remote.GetSeasonAnime
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.AnimeMapper
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.util.ExceptionHandler
import com.lexwilliam.risutov2.util.getCurrentSeason
import com.lexwilliam.risutov2.util.getCurrentYear
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
        private val seasonAnimeUseCase: GetSeasonAnime,
        private val animeMapper: AnimeMapper
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
            seasonAnimeUseCase.execute(null, null).collect { results ->
                _state.value = _state.value.copy(season = results.season_name, year = results.season_year)
                val animes = results.anime.map { anime -> animeMapper.toPresentation(anime) }
                _state.value = _state.value.copy(seasonAnimes = animes)
            }
        }
    }

    private fun onSeasonAnime() {
        seasonJob?.cancel()
        seasonJob = launchCoroutine {
            seasonAnimeUseCase.execute(_state.value.year, _state.value.season).collect { results ->
                val animes = results.anime.map { anime -> animeMapper.toPresentation(anime) }
                _state.value = _state.value.copy(seasonAnimes = animes, year = _state.value.year, season = _state.value.season)
            }
        }
    }

    fun setSeason(str: String) {
        val strToSeason = str.split(" ")
        _state.value = _state.value.copy(season = strToSeason.first().decapitalize(Locale.ROOT))
        _state.value = _state.value.copy(year = strToSeason.last().toInt())
        onSeasonAnime()
    }
}

data class SeasonViewState(
    val season: String = "",
    val year: Int = -1,
    val seasonAnimes: List<AnimePresentation> = emptyList()
)