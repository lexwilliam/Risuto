package com.lexwilliam.risuto.ui.screens.people

import androidx.compose.runtime.Composable
import com.lexwilliam.risuto.model.PersonPresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen

@Composable
fun PeopleScreen(
    state: PeopleContract.State
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        PeopleContent(person = state.person)
    }
}

@Composable
fun PeopleContent(
    person: PersonPresentation
) {

}