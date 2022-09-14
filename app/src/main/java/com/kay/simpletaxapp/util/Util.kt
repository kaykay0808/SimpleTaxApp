package com.kay.simpletaxapp.util

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

fun sliderToPercentage(value: Float): Int = (value * 100).roundToInt()

fun formatCurrency(number: Double): String =
    NumberFormat.getCurrencyInstance(Locale.forLanguageTag("nb-NO"))
        .format(number)

// val totalAfterTaxFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("nb-NO"))
//    .format(incomeAfterTax)/*"%.2f".format(incomeAfterTax)*/
/*"%.2f".format(incomeAfterTax)*/
