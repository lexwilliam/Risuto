package com.example.risuto.presentation.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private val seasons = arrayListOf(
    "winter", "winter", "spring","spring", "spring", "summer",
    "summer", "summer", "fall", "fall", "fall", "winter"
)

@SuppressLint("SimpleDateFormat")
fun getCurrentMonth(): Int {
    val sdf = SimpleDateFormat("MM")
    val currentMonth = sdf.format(Date())
    return currentMonth.toInt()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentYear(): Int {
    val sdf = SimpleDateFormat("yyyy")
    val currentYear = sdf.format(Date())
    return currentYear.toInt()
}

val thisSeason = seasons[getCurrentMonth() - 1]