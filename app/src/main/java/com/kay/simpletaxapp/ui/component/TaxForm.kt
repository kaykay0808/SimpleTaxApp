package com.kay.simpletaxapp.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.kay.simpletaxapp.R
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel
import com.kay.simpletaxapp.ui.viewmodel.TaxViewState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaxForm(
    viewState: TaxViewState,
    taxViewModel: TaxViewModel,
    percentage: Int,
) {
    // Valid state if totalBillState is not empty
    val validState = remember(viewState.netSalaryString) {
        viewState.netSalaryString.trim()
            .isNotEmpty() /* <- Returns a boolean "if it's not empty it will return true"*/
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    IncomeAfterTaxHeader(incomeAfterTax = viewState.incomeAfterTax)

    SalaryInputField(
        inputValueState = viewState.netSalaryString,
        labelId = stringResource(id = R.string.input_field_label),
        valueChanged = { newInputVal -> taxViewModel.onInputValueChange(newInputVal) },
        valueReset = { taxViewModel.onResetInputValueChange() },
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            keyboardController?.hide()
        }
    )
    TaxInfo(
        percentage = percentage,
        viewState = viewState
    )
    TaxSlider(
        valueChanged = { newVal -> taxViewModel.onSliderValueChange(newVal) },
        sliderPositionState = viewState.sliderValue,
    )
}