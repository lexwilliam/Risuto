package com.lexwilliam.risutov2.ui.theme

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = bluePrimary,
    primaryVariant = blueLight,
    secondary = Color.White,
    secondaryVariant = Color.Black,
    background = Gray900,
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
    Log.d("TAG", darkTheme.toString())
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = RisutoTypography,
        shapes = Shapes,
        content = content
    )
}