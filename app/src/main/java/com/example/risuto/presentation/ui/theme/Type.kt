package com.example.risuto.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.risuto.R

val ProximaNova = FontFamily(
    Font(R.font.proximanova_regular),
    Font(R.font.proximanova_black, FontWeight.W500),
    Font(R.font.proximanova_bold, FontWeight.Bold),
    Font(R.font.proximanova_thin, FontWeight.Thin),
    Font(R.font.proximanova_extrabold, FontWeight.ExtraBold)
)

// Set of Material typography styles to start with
val Typography = Typography(defaultFontFamily = ProximaNova)