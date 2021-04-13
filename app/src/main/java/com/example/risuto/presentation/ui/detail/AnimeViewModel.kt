package com.example.risuto.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.domain.usecase.GetAnimeUseCase
import com.example.risuto.domain.usecase.GetCharacterStaffUseCase
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.CharacterStaffPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel
@Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val getCharacterStaffUseCase: GetCharacterStaffUseCase,
    savedState: SavedStateHandle
): ViewModel() {

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    private val _state = MutableStateFlow(AnimeViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            malIdFromArgs?.let { id ->
                if (id > 0) {
                    getAnimeUseCase.invoke(id).collect { results ->
                        val animes = results.toPresentation()
                        _state.value = _state.value.copy(animeDetail = animes)
                    }
                    getCharacterStaffUseCase.invoke(id).collect { results ->
                        val staffs = results.toPresentation()
                        _state.value = _state.value.copy(animeStaff = staffs)
                    }
                }
            }
        }
    }
}

data class AnimeViewState(
    val animeDetail: AnimePresentation = AnimePresentation(),
    val animeStaff: CharacterStaffPresentation = CharacterStaffPresentation()
)