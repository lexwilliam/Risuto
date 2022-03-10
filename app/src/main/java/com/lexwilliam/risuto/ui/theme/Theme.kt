package com.lexwilliam.risuto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = bluePrimary,
    primaryVariant = blueLight,
    secondary = Color.White,
    secondaryVariant = Color.White,
    background = Color.Black,
    surface = Gray600,
    error = redLight
)

private val LightColorPalette = lightColors(
    primary = bluePrimary,
    primaryVariant = blueLight,
    secondary = Color.Black,
    secondaryVariant = Color.White,
    background = Color.White,
    surface = Gray300,
    error = redDark
)

@Composable
fun RisutoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors: Colors
    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
        colors = DarkColorPalette
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = false
        )
        colors = LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = RisutoTypography,
        shapes = Shapes,
        content = content
    )
}