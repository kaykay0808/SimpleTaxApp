package com.kay.simpletaxapp.util

import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

fun sliderToPercentage(value: Float): Int = (value * 100).roundToInt()

fun formatCurrency(number: Double): String = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("nb-NO"))
    .format(number)
