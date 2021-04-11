package com.example.risuto.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
val Typography = Typography(
    defaultFontFamily = ProximaNova,
    h1 = TextStyle(fontSize = 96.sp),
    h2 = TextStyle(fontSize = 60.sp),
    h3 = TextStyle(fontSize = 48.sp),
    h4 = TextStyle(fontSize = 34.sp),
    h5 = TextStyle(fontSize = 24.sp),
    h6 = TextStyle(fontSize = 20.sp),
    subtitle1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
    subtitle2 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
    body1 = TextStyle(fontSize = 16.sp),
    body2 = TextStyle(fontSize = 14.sp),
    button = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
    caption = TextStyle(fontSize = 12.sp),
    overline = TextStyle(fontSize = 10.sp),
)