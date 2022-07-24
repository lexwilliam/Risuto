package com.lexwilliam.risuto.ui.screens.profile

import androidx.compose.runtime.Composable
import com.lexwilliam.risuto.model.UserProfilePresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen

@Composable
fun ProfileScreen(
    state: ProfileContract.State
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        ProfileContent(
            userProfile = state.userProfile
        )
    }

}

@Composable
fun ProfileContent(
    userProfile: UserProfilePresentation
) {

}