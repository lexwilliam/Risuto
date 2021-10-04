package com.example.risuto.presentation.ui.genre

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.data.remote.repository.paged.GenreListSource
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GenreViewModel
@Inject constructor(
    private val listRepository: ListRepository,
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
        genreIdFromArgs.let {
            if (it != null) {
                listRepository.currentGenre = it
                _state.value = _state.value.copy(genreIndex = it)
            }
        }
    }

    val animes: Flow<PagingData<AnimeListPresentation>> = Pager(PagingConfig(pageSize = 20)) {
        GenreListSource(listRepository)
    }.flow.cachedIn(viewModelScope)

}

data class GenreViewState(
    val genreIndex: Int? = null,
    val onLoading: Boolean = true
)