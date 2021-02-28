package com.chun2maru.risutomvvm.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SearchViewModel
@ViewModelInject
constructor(
        private val searchAnimeUseCase: SearchAnimeUseCase,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var searchAnimeViewState by mutableStateOf(listOf<SearchAnimePresentation>())
        private set

    fun executeAnimeSearch(animeName: String) {
        viewModelScope.launch {
            searchAnimeUseCase.invoke(animeName).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                searchAnimeViewState = animes
            }
        }
    }

}
