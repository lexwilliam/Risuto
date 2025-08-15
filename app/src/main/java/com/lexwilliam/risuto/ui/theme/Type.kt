package com.lexwilliam.risuto.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.lexwilliam.risuto.R

val ProximaNova = FontFamily(
    Font(R.font.proximanova_thin, FontWeight.Light),
    Font(R.font.proximanova_regular, FontWeight.Normal),
    Font(R.font.proximanova_bold, FontWeight.Bold),
    Font(R.font.proximanova_black, FontWeight.Black)
)

val RisutoTypography = Typography(
    defaultFontFamily = ProximaNova
//    h1 = TextStyle(
//        fontSize = 104.sp,
//        fontWeight = FontWeight.Light,
//        letterSpacing = (-1.5).sp
//    ),
//    h2 = TextStyle(
//        fontSize = 65.sp,
//        fontWeight = FontWeight.Light,
//        letterSpacing = (-0.5).sp
//    ),
//    h3 = TextStyle(
//        fontSize = 52.sp,
//        fontWeight = FontWeight.Normal
//    ),
//    h4 = TextStyle(
//        fontSize = 37.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 0.25.sp
//    ),
//    h5 = TextStyle(
//        fontSize = 26.sp,
//        fontWeight = FontWeight.Normal
//    ),
//    h6 = TextStyle(
//        fontSize = 22.sp,
//        fontWeight = FontWeight.Bold,
//        letterSpacing = 0.15.sp
//    ),
//    subtitle1 = TextStyle(
//        fontSize = 17.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 0.15.sp
//    ),
//    subtitle2 = TextStyle(
//        fontSize = 15.sp,
//        fontWeight = FontWeight.Bold,
//        letterSpacing = 0.1.sp
//    ),
//    body1 = TextStyle(
//        fontSize = 17.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 0.5.sp
//    ),
//    body2 = TextStyle(
//        fontSize = 15.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 0.25.sp
//    ),
//    button = TextStyle(
//        fontSize = 15.sp,
//        fontWeight = FontWeight.Bold,
//        letterSpacing = 1.25.sp
//    ),
//    caption = TextStyle(
//        fontSize = 13.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 0.4.sp
//    ),
//    overline = TextStyle(
//        fontSize = 11.sp,
//        fontWeight = FontWeight.Normal,
//        letterSpacing = 1.5.sp
//    )
)