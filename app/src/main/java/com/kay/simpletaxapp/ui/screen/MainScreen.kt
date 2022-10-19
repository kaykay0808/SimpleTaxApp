package com.kay.simpletaxapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kay.simpletaxapp.component.TaxForm
import com.kay.simpletaxapp.ui.theme.LARGE_PADDING
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel

@Composable
fun MainScreen(taxViewModel: TaxViewModel) {
    val viewState = taxViewModel.viewState
    // val sliderPercentage = sliderToPercentage(viewState.sliderValue)

    Column(modifier = Modifier.padding(all = LARGE_PADDING)) {
        TaxForm(
            taxViewModel = taxViewModel,
            viewState = viewState,
            // percentage = sliderPercentage
            // sliderPositionState = viewState.sliderPositionState,
            // totalSalaryAmountState = viewState.totalSalaryAmountState,
            // totalIncomeAfterTax = viewState.totalIncomeAfterTax,
            // taxPay = viewState.taxAmountState,
        ) // {}
    }
}
