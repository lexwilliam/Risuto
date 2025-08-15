package com.lexwilliam.risuto.ui.screens.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetCharacterById
import com.lexwilliam.risuto.mapper.CharacterMapper
import com.lexwilliam.risuto.model.CharacterDetailPresentation
import com.lexwilliam.risuto.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterById: GetCharacterById,
    private val characterMapper: CharacterMapper,
    savedState: SavedStateHandle
): BaseViewModel<CharacterContract.Event, CharacterContract.State, CharacterContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private val malIdFromArgs = savedState.get<Int>("mal_id")

    init {
        malIdFromArgs?.let { id ->
            getCharacterById(id)
        }
    }

    override fun setInitialState(): CharacterContract.State {
        return CharacterContract.State(
            character = getInitialCharacter().data,
            isLoading = true,
            isError = false
        )
    }
    private fun getCharacterById(id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getCharacterById.execute(id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        characterMapper.toPresentation(it)
                            .let { character ->
                                setState {
                                    copy(
                                        character = character.data,
                                        isLoading = false
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }


    override fun handleEvents(event: CharacterContract.Event) {
        TODO("Not yet implemented")
    }

    private fun handleExceptions(throwable: Throwable) {
        Timber.e(throwable)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private fun getInitialCharacter() =
        CharacterDetailPresentation(
            CharacterDetailPresentation.Data(
                "", emptyList(), -1, -1, "", "",
                "", emptyList()
            )
        )

}