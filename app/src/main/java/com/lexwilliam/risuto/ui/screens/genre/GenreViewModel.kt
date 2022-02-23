package com.lexwilliam.risuto.ui.screens.genre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.lexwilliam.domain.usecase.remote.GetGenreAnime
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.AnimeMapper
import com.lexwilliam.risuto.model.AnimeListPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenreViewModel
@Inject constructor(
    private val getGenreAnime: GetGenreAnime,
    private val animeMapper: AnimeMapper,
    savedState: SavedStateHandle
): BaseViewModel<GenreContract.Event, GenreContract.State, GenreContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private var genreJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        genreJob?.cancel()
    }

    private val genreIdFromArgs = savedState.get<Int>("genre_id")

    override fun setInitialState(): GenreContract.State {
        return GenreContract.State(
            animes = null,
            isLoading = true,
            isError = false
        )
    }

    init {
        genreIdFromArgs?.let {
            setState {
                copy(
                    animes = genreAnimes(it),
                    genreId = it,
                    isLoading = false
                )
            }
        }
    }

    override fun handleEvents(event: GenreContract.Event) {}

    private fun genreAnimes(genre: Int): Flow<PagingData<AnimeListPresentation>> {
        return getGenreAnime.execute(null, null, null, genre, "members", "desc")
            .map { it.map { animeMapper.toPresentation(it) } }
            .cachedIn(viewModelScope)
    }

}