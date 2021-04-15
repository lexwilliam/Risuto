package com.example.risuto.presentation.ui.genre

import androidx.lifecycle.SavedStateHandle
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class GenreViewModel
@Inject constructor(
    private val searchAnimeUseCase: SearchAnimeUseCase,
    savedState: SavedStateHandle
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var searchJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    private val genreIdFromArgs = savedState.get<Int>("genre_id")

    private var _state = MutableStateFlow(GenreViewState())
    val state = _state.asStateFlow()

    init {
        searchJob?.cancel()
        searchJob = launchCoroutine {
            genreIdFromArgs?.let { id ->
                if(id > 0) {
                    searchAnimeUseCase.invoke(QuerySearch(genre = id, order_by = "members")).collect { results ->
                        val animes = results.map { anime -> anime.toPresentation() }
                        _state.value = _state.value.copy(animes, id - 1, false)
                    }
                }
            }
        }
    }
}

data class GenreViewState(
    val genreAnimes: List<AnimeListPresentation> = emptyList(),
    val genreIndex: Int? = null,
    val onLoading: Boolean = true
)