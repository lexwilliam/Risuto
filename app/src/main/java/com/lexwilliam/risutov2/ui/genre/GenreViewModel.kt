package com.lexwilliam.risutov2.ui.genre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.remote.GetGenreAnime
import com.lexwilliam.risutov2.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenreViewModel
@Inject constructor(
    private val getGenreAnime: GetGenreAnime,
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
    private val perPage = 20

    override fun setInitialState(): GenreContract.State {
        return GenreContract.State(
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: GenreContract.Event) {
        TODO("Not yet implemented")
    }

    init {
        genreIdFromArgs?.let {
            genreAnimes(it)
        }
    }

    fun genreAnimes(genre: Int) {
        viewModelScope.launch(errorHandler) {
            val animes = getGenreAnime.execute(viewModelScope, genre, perPage)
            setState {
                copy(
                    animes = animes,
                    genreId = genre
                )
            }
        }
    }

}