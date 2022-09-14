package com.kay.simpletaxapp.ui.viewmodel

data class TaxViewState(
    val netSalaryString: String = "", // InputField/Net (totalSalaryAmountState)
    val netSalaryDouble: Double = 0.0,
    val incomeAfterTax: Double = 0.0, // The Header
    val taxAmount: Double = 0.0, // Tax Pay
    val sliderValue: Float = 0f, // (sliderPositionState)
    val taxPercentage: Int = 25 // Adding percentage as a dataClass instead.
    // val resetAmountState: String = "",
)
