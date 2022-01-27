package com.lexwilliam.risutov2.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.lexwilliam.domain.usecase.local.InsertAnimeHistory
import com.lexwilliam.domain.usecase.local.InsertMyAnime
import com.lexwilliam.domain.usecase.remote.GetAnimeDetail
import com.lexwilliam.domain.usecase.remote.GetCharacterStaff
import com.lexwilliam.risutov2.base.BaseViewModel
import com.lexwilliam.risutov2.mapper.DetailMapper
import com.lexwilliam.risutov2.mapper.HistoryMapper
import com.lexwilliam.risutov2.mapper.MyAnimeMapper
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.detail.CharacterStaffPresentation
import com.lexwilliam.risutov2.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel
@Inject constructor(
    private val getAnimeDetail: GetAnimeDetail,
    private val getCharacterStaff: GetCharacterStaff,
    private val insertAnimeHistory: InsertAnimeHistory,
    private val insertMyAnime: InsertMyAnime,
    private val detailMapper: DetailMapper,
    private val myAnimeMapper: MyAnimeMapper,
    private val historyMapper: HistoryMapper,
    savedState: SavedStateHandle
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var detailJob: Job? = null
    private var insertMyAnimeJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        detailJob?.cancel()
    }

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    private val animeDetail = MutableStateFlow(AnimeDetailPresentation())
    private val animeStaff = MutableStateFlow(CharacterStaffPresentation(emptyList(), emptyList()))
    private val _state = MutableStateFlow(AnimeViewState())
    val state = _state.asStateFlow()

    init {
        detailJob?.cancel()
        detailJob = launchCoroutine {
            malIdFromArgs?.let { id ->
                if (id > 0) {
                    getAnimeDetail.execute(id).collect { results ->
                        val animes = detailMapper.toPresentation(results)
                        animeDetail.value = animes
                        insertAnimeHistory.execute(historyMapper.toDomain(animes))
                    }
                    getCharacterStaff.execute(id).collect { results ->
                        val staffs = detailMapper.toPresentation(results)
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

    fun insertToMyAnime(
        anime: AnimePresentation
    ) {
        insertMyAnimeJob?.cancel()
        insertMyAnimeJob = launchCoroutine {
            insertMyAnime.execute(myAnimeMapper.toDomain(anime))
        }
    }
}

data class AnimeViewState(
    val animeDetail: AnimeDetailPresentation = AnimeDetailPresentation(),
    val animeStaff: CharacterStaffPresentation = CharacterStaffPresentation(emptyList(), emptyList()),
    val onLoading: Boolean = true
)