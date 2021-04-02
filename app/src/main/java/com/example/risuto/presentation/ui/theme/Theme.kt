package com.example.risuto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Gray900,
    primaryVariant = Gray600,
    secondary = Gray600,
    secondaryVariant = Gray500,
    background = Gray800,
    surface = Gray800,
    error = ErrorRed

)

private val LightColorPalette = lightColors(
    primary = primaryLight,
    primaryVariant = primaryVariant,
    secondary = secondaryLight,
    secondaryVariant = secondaryVariant,
    background = Gray50,
    surface = Gray50,
    error = ErrorRed
)

@Composable
fun RisutoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}