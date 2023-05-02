package com.lexwilliam.risuto.ui.screens.character

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CharacterScreen(
    state: CharacterContract.State,
    onBackPressed: () -> Unit
) {
    Text(text = state.character.toString())
}