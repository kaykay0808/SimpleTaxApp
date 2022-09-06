package com.kay.simpletaxapp.util

import kotlin.math.roundToInt

fun sliderToPercentage(value: Float): Int = (value *100).roundToInt()