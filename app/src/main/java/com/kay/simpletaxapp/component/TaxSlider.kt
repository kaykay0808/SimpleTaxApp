package com.kay.simpletaxapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel

@Composable
fun TaxSlider(
    viewModel: TaxViewModel,
    sliderPositionState: Float,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPositionState,
            onValueChange = { newVal ->
                viewModel.onSliderValueChange(newVal)
            }
        )
    }
}

@Preview
@Composable
fun TaxSliderPreview() {
    val taxViewModel = TaxViewModel()
    val viewState = taxViewModel.viewState

    TaxSlider(
        viewModel = taxViewModel,
        sliderPositionState = viewState.sliderPositionState,
    )
}