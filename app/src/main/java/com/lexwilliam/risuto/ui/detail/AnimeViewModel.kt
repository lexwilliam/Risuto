package com.lexwilliam.risuto.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.local.InsertAnimeHistory
import com.lexwilliam.domain.usecase.local.InsertMyAnime
import com.lexwilliam.domain.usecase.remote.GetAnimeDetail
import com.lexwilliam.domain.usecase.remote.GetCharacterStaff
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.DetailMapper
import com.lexwilliam.risuto.mapper.HistoryMapper
import com.lexwilliam.risuto.mapper.MyAnimeMapper
import com.lexwilliam.risuto.model.common.*
import com.lexwilliam.risuto.model.detail.AnimeDetailPresentation
import com.lexwilliam.risuto.model.detail.CharacterStaffPresentation
import com.lexwilliam.risuto.model.local.MyAnimePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeDetail: GetAnimeDetail,
    private val getCharacterStaff: GetCharacterStaff,
    private val insertAnimeHistory: InsertAnimeHistory,
    private val insertMyAnime: InsertMyAnime,
    private val detailMapper: DetailMapper,
    private val myAnimeMapper: MyAnimeMapper,
    private val historyMapper: HistoryMapper,
    savedState: SavedStateHandle
): BaseViewModel<AnimeContract.Event, AnimeContract.State, AnimeContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private var detailJob: Job? = null
    private var characterStaffJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        detailJob?.cancel()
    }

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    override fun setInitialState(): AnimeContract.State {
        return AnimeContract.State(
            animeDetail = getInitialStateAnimeDetail(),
            characterStaff = getInitialStateCharacterStaff(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: AnimeContract.Event) {
        when(event) {
            is AnimeContract.Event.InsertAnimeHistory -> insertAnimeHistory(event.anime)
            is AnimeContract.Event.InsertMyAnime -> insertMyAnime(event.anime)
        }
    }

    init {
        malIdFromArgs?.let { id ->
            animeDetail(id)
            characterStaff(id)
        }
    }

    private fun animeDetail(mal_id: Int) {
        detailJob?.cancel()
        detailJob = viewModelScope.launch(errorHandler) {
            try {
                getAnimeDetail.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { detail ->
                        detailMapper.toPresentation(detail)
                            .let { animeDetail ->
                                setState {
                                    copy(
                                        animeDetail = animeDetail,
                                        isLoading = false
                                    )
                                }
                                insertAnimeHistory(animeDetail)
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun characterStaff(mal_id: Int) {
        characterStaffJob?.cancel()
        characterStaffJob = viewModelScope.launch(errorHandler) {
            try {
                getCharacterStaff.execute(mal_id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        detailMapper.toPresentation(it)
                            .let { characterStaff ->
                                setState {
                                    copy(
                                        characterStaff = characterStaff
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun insertAnimeHistory(anime: AnimeDetailPresentation) {
        viewModelScope.launch(errorHandler) {
            insertAnimeHistory.execute(historyMapper.toDomain(anime))
        }
    }

    private fun insertMyAnime(
        myAnime: MyAnimePresentation
    ) {
        viewModelScope.launch(errorHandler) {
            insertMyAnime.execute(myAnimeMapper.toDomain(myAnime))
        }
    }

    private fun getInitialStateAnimeDetail() =
        AnimeDetailPresentation(
            AiredPresentation("", PropPresentation(FromPresentation(-1,-1,-1), ToPresentation(-1,-1,-1)),"", ""),
            false, "", "", "", listOf(""), -1,
            -1, listOf(GenrePresentation(-1,"", "", "")), "",
            listOf(LicensorPresentation(-1, "", "", "")), -1,-1,
            listOf(""), -1, "", listOf(ProducerPresentation(-1, "","", "" )),
            -1, "", RelatedPresentation(), -1, false, "", 0.0, -1,
            "", "", listOf(StudioPresentation(-1, "", "", "")), "",
            "", "", "", listOf(""), "", "", ""
        )

    private fun getInitialStateCharacterStaff() =
        CharacterStaffPresentation(emptyList(), emptyList())

    private fun handleExceptions(throwable: Throwable) {
        Timber.e(throwable)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }
}