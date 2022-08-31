package com.kay.simpletaxapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Green = Color(0xFF8BC34A)
val Black = Color(0xFF252724)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val Colors.topHeaderBackground: Color
    @Composable
    get() = if (isLight) Green else Black

val Colors.topHeaderTextColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray
