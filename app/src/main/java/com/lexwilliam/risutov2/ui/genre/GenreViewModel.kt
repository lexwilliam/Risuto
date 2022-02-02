package com.lexwilliam.risutov2.ui.genre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.usecase.remote.GetGenreAnime
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.AnimeMapper
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.ui.search.SearchContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
            Timber.d(it.toString())
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

    private fun genreAnimes(genre: Int): Flow<PagingData<AnimePresentation>> {
        return getGenreAnime.execute(null, genre)
            .map { it.map { animeMapper.toPresentation(it) } }
            .cachedIn(viewModelScope)
    }

}