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

@Composable
fun MainContent() {

    val totalSalaryAmountState = remember {
        mutableStateOf("")
    }

    val taxAmountState = remember {
        mutableStateOf(0.0)
    }

    val totalIncomeAfterTax = remember {
        mutableStateOf(0.0)
    }

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val salaryPercentage = (sliderPositionState.value * 100).toInt()

    Column(modifier = Modifier.padding(all = LARGE_PADDING)) {
        // TaxForm
        TaxForm(
            totalSalaryAmountState = totalSalaryAmountState,
            taxPay = taxAmountState,
            totalIncomeAfterTax = totalIncomeAfterTax,
            sliderPositionState = sliderPositionState,
            percentage = salaryPercentage
        ) {}
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent()
}
