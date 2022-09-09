package com.kay.simpletaxapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaxSlider(
    valueChanged: (Float) -> Unit,
    sliderPositionState: Float,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPositionState,
            onValueChange = valueChanged,
        )
    }
}

@Preview
@Composable
fun TaxSliderPreview() {
    TaxSlider(
        valueChanged = {},
        sliderPositionState = 25f,
    )
}