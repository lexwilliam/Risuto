package com.lexwilliam.risutov2.ui.profile

import com.lexwilliam.domain.usecase.local.GetMyAnimeWithWatchStatus
import com.lexwilliam.domain.usecase.local.GetMyAnimes
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.MyAnimeMapper
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.util.ExceptionHandler
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
    private val getMyAnimes: GetMyAnimes,
    private val getMyAnimeWithWatchStatus: GetMyAnimeWithWatchStatus,
    private val myAnimeMapper: MyAnimeMapper
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
            getMyAnimes.execute().collect { results ->
                val animes = results.map { myAnimeMapper.toPresentation(it) }
                _state.value = _state.value.copy(myAnimeList = animes)
            }
        }
    }
}

data class ProfileViewState(
    val myAnimeList: List<AnimePresentation> = emptyList()
)