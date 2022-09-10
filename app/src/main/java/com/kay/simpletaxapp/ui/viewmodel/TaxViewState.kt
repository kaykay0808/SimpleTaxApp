package com.kay.simpletaxapp.ui.viewmodel

data class TaxViewState(
    val netSalaryString: String = "", // InputField/Net
    val netSalary: Double = 0.0,
    val incomeAfterTax: Double = 0.0, // The Header
    val taxAmount: Double = 0.0, // Tax Pay
    val sliderValue: Float = 0.25f,
    val taxPercentage: Int = 25
)
