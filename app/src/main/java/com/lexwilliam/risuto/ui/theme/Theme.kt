package com.lexwilliam.risuto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = darkBackground,
    surface = Gray600,
    error = redLight,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = secondary,
    primaryVariant = secondaryVariant,
    secondary = primary,
    secondaryVariant = primaryVariant,
    background = Color.White,
    surface = Gray300,
    error = redDark,
)

@Composable
fun RisutoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors: Colors
    if (darkTheme) {
        colors = DarkColorPalette
    } else {
        colors = LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = RisutoTypography,
        shapes = Shapes,
        content = content
    )
}