package com.kay.simpletaxapp.data

data class TaxViewState(
    val totalSalaryAmountState: String = "", // InputField/Net
    val totalIncomeAfterTax: Double = 0.0, // The Header
    val taxAmountState: Double = 0.0, // Tax Pay
    val sliderPositionState: Float = 0f
)
