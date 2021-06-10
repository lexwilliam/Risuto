package com.example.risuto.presentation.ui.profile

import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.domain.usecase.local.GetMyAnimesUseCase
import com.example.risuto.domain.usecase.local.GetMyAnimesWithWatchStatusUseCase
import com.example.risuto.presentation.base.BaseViewModel
import com.example.risuto.presentation.model.MyAnimePresentation
import com.example.risuto.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val getMyAnimesUseCase: GetMyAnimesUseCase,
    private val getMyAnimesWithWatchStatusUseCase: GetMyAnimesWithWatchStatusUseCase
) : BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var profileJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        profileJob?.cancel()
    }

    private val _state = MutableStateFlow(ProfileViewState())
    val state = _state.asStateFlow()

    init {
        profileJob?.cancel()
        profileJob = launchCoroutine {
            getMyAnimesUseCase.invoke().collect { results ->
                val myAnime = results.map { it.toPresentation() }
                _state.value = _state.value.copy(myAnimeList = myAnime)
            }
        }
    }
}

data class ProfileViewState(
    val myAnimeList: List<MyAnimePresentation> = emptyList()
)