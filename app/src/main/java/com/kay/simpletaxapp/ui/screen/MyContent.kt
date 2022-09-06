package com.kay.simpletaxapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kay.simpletaxapp.component.TaxForm
import com.kay.simpletaxapp.ui.theme.LARGE_PADDING
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel
import com.kay.simpletaxapp.util.sliderToPercentage
import kotlin.math.roundToInt

@Composable
fun MainContent( taxViewModel: TaxViewModel) {

    val viewState = taxViewModel.viewState
    val sliderPercentage = sliderToPercentage(viewState.sliderPositionState)

    Column(modifier = Modifier.padding(all = LARGE_PADDING)) {
        // TaxForm
        TaxForm(
            taxViewModel = taxViewModel,
            viewState = viewState,
            sliderPositionState = viewState.sliderPositionState,
            percentage = sliderPercentage
        ) {}
    }
}

