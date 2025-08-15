package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun ImeAvoidingBox() {
    val insets = LocalWindowInsets.current

    val imeBottom = with(LocalDensity.current) { insets.ime.bottom.toDp() }
    Box(Modifier.padding(bottom = imeBottom))
}

@Composable
fun StatusBarSpacer() {
    Box(
        Modifier
            .statusBarsHeight() // Match the height of the status bar
            .fillMaxWidth()
    )
}