package com.lexwilliam.risuto.ui.screens.person

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetPeopleById
import com.lexwilliam.risuto.base.BaseViewModel
import com.lexwilliam.risuto.mapper.PersonMapper
import com.lexwilliam.risuto.util.getInitialStatePerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject constructor(
    private val getPeopleById: GetPeopleById,
    private val personMapper: PersonMapper,
    savedState: SavedStateHandle
): BaseViewModel<PersonContract.Event, PersonContract.State, PersonContract.Effect>() {
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

    override fun setInitialState(): PersonContract.State {
        return PersonContract.State(
            person = getInitialStatePerson().data,
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: PersonContract.Event) {

    }

    init {
        malIdFromArgs?.let { id ->
            getPeopleById(id)
        }
    }

    private fun getPeopleById(id: Int) {
        viewModelScope.launch(errorHandler) {
            try {
                getPeopleById.execute(id)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect {
                        personMapper.toPresentation(it)
                            .let { person ->
                                setState {
                                    copy(
                                        person = person.data,
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