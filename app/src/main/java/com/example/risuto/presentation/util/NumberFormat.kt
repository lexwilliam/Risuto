package com.example.risuto.presentation.util

import java.text.NumberFormat
import java.util.*

internal fun intToCurrency(int: Int): String {
    return NumberFormat.getNumberInstance(Locale.ENGLISH).format(int)
}