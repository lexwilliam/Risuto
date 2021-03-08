package com.example.risuto.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.risuto.domain.usecase.GetAnimeUseCase
import com.example.risuto.presentation.model.AnimePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject constructor(
        private val getAnimeUseCase: GetAnimeUseCase,
        private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    var animeViewState by mutableStateOf(listOf<AnimePresentation>())
        private set
}