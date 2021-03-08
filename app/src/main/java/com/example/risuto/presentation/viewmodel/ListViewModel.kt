package com.chun2maru.risutomvvm.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun2maru.risutomvvm.domain.usecase.SearchAnimeUseCase
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import com.example.risuto.domain.usecase.TopAnimeUseCase
import com.example.risuto.presentation.model.TopItemPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject constructor(
    private val searchAnimeUseCase: SearchAnimeUseCase,
    private val topAnimeUseCase: TopAnimeUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var searchAnimeViewState by mutableStateOf(listOf<SearchAnimePresentation>())
        private set

    var getTopResultViewState by mutableStateOf(listOf<TopItemPresentation>())
        private set

    fun executeAnimeSearch(animeName: String) {
        viewModelScope.launch {
            searchAnimeUseCase.invoke(animeName).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                searchAnimeViewState = animes
            }
        }
    }

    fun executeTopResult(type: String, page: Int, subType: String) {
        viewModelScope.launch {
            topAnimeUseCase.invoke(type, page, subType).collect { results ->
                val animes = results.map { anime -> anime.toPresentation() }
                getTopResultViewState = animes
            }
        }
    }
}
