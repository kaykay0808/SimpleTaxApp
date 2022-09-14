package com.kay.simpletaxapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaxSlider(
    // viewModel: TaxViewModel,
    valueChanged: (Float) -> Unit,
    sliderPositionState: Float
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPositionState,
            onValueChange = valueChanged // { newVal -> viewModel.onSliderValueChange(newVal) }
        )
    }
}

@Preview
@Composable
fun TaxSliderPreview() {
    TaxSlider(
        // viewModel = taxViewModel,
        valueChanged = {},
        sliderPositionState = .50f
    )
}
