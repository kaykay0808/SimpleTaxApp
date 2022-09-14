package com.kay.simpletaxapp.component

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
    // modifier: Modifier = Modifier,
    viewState: TaxViewState,
    taxViewModel: TaxViewModel,
    // percentage: Int,
    // sliderPositionState: Float,
    // onValChange: (String) -> Unit
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
        // viewModel = taxViewModel,
        valueChanged = { newInputVal -> taxViewModel.onInputValueChange(newInputVal) },
        valueReset = { taxViewModel.onResetInputValueChange() },
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            // Todo - onValuedChanged
            // onValChange(viewState.totalSalaryAmountState.trim())
            keyboardController?.hide()
        }
    )
    TaxInfo(viewState = viewState)
    TaxSlider(
        // viewModel = taxViewModel,
        valueChanged = { newVal -> taxViewModel.onSliderValueChange(newVal) },
        sliderPositionState = viewState.sliderValue,
    )
    /*if (validState) {
    } else {
        Box {
            // show a sad empty box
        }
    }*/
}
