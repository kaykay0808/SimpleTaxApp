package com.kay.simpletaxapp.data

data class TaxViewState(
    val totalSalaryAmountState: String ="",
    val onSalaryValueChange: String = "",
    val taxAmountState: Double = 0.0,
    val totalIncomeAfterTax: Double = 0.0,
    val sliderPositionState: Float = 0f
)