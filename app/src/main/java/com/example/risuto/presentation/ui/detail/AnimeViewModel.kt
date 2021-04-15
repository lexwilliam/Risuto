package com.example.risuto.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.domain.usecase.GetAnimeUseCase
import com.example.risuto.domain.usecase.GetCharacterStaffUseCase
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.CharacterStaffPresentation
import com.example.risuto.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel
@Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val getCharacterStaffUseCase: GetCharacterStaffUseCase,
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

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    private val animeDetail = MutableStateFlow(AnimePresentation())
    private val animeStaff = MutableStateFlow(CharacterStaffPresentation())
    private val _state = MutableStateFlow(AnimeViewState())
    val state = _state.asStateFlow()

    init {
        searchJob?.cancel()
        searchJob = launchCoroutine {
            malIdFromArgs?.let { id ->
                if (id > 0) {
                    getAnimeUseCase.invoke(id).collect { results ->
                        val animes = results.toPresentation()
                        animeDetail.value = animes
                    }
                    getCharacterStaffUseCase.invoke(id).collect { results ->
                        val staffs = results.toPresentation()
                        animeStaff.value = staffs
                    }
                    combine(
                        animeDetail,
                        animeStaff
                    ) { animeDetail, animeStaff ->
                        AnimeViewState(
                            animeDetail = animeDetail,
                            animeStaff = animeStaff,
                            onLoading = false
                        )
                    }.catch {
                        throw it
                    }.collect {
                        _state.value = it
                    }
                }
            }
        }
    }
}

data class AnimeViewState(
    val animeDetail: AnimePresentation = AnimePresentation(),
    val animeStaff: CharacterStaffPresentation = CharacterStaffPresentation(),
    val onLoading: Boolean = true
)