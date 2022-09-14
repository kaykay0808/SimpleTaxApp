package com.kay.simpletaxapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kay.simpletaxapp.util.sliderToPercentage

class TaxViewModel : ViewModel() {
    var viewState by mutableStateOf(TaxViewState())
        private set

    private var netSalaryString = ""
    private var netSalaryDouble = 0.0
    private var incomeAfterTax = 0.0
    private var taxAmount = 0.0
    private var sliderValue = 0.25f

    private fun render(/*copy: TaxViewState.() -> TaxViewState*/) {
        val taxPercentage = sliderToPercentage(sliderValue)
        viewState = /*copy(viewState)*/ TaxViewState(
            netSalaryString = netSalaryString,
            netSalaryDouble = netSalaryDouble,
            incomeAfterTax = calculateSalaryAfterTax(
                totalSalary = netSalaryDouble,
                percentage = taxPercentage
            ),
            taxAmount = calculateTotalTax(
                totalSalary = netSalaryDouble,
                percentage = taxPercentage
            ),
            sliderValue = sliderValue,
            taxPercentage = taxPercentage
        )
    }

    private fun calculateTotalTax(
        totalSalary: Double,
        percentage: Int
    ): Double {
        return if (totalSalary > 1 && totalSalary.toString().isNotEmpty()) {
            (totalSalary * percentage) / 100
        } else {
            0.0
        }
    }

    private fun calculateSalaryAfterTax(
        totalSalary: Double,
        percentage: Int
    ): Double {
        val salaryAfterTax = calculateTotalTax(
            totalSalary = totalSalary,
            percentage = percentage
        )
        return (totalSalary - salaryAfterTax)
    }

    fun onSliderValueChange(newVal: Float) {
        /*val taxPay = calculateTotalTax(
            totalSalary = viewState.netSalaryDouble, // viewState.netSalaryString.toDouble(),
            percentage = sliderToPercentage(newVal)
        )
        val salaryAfterTax = calculateSalaryAfterTax(
            totalSalary = viewState.netSalaryDouble, // viewState.netSalaryString.toDouble(),
            percentage = sliderToPercentage(newVal)
        )*/
        sliderValue = newVal
        render()
    }

    fun onInputValueChange(newInputVal: String) {
        netSalaryString = newInputVal
        netSalaryDouble = newInputVal.toDoubleOrNull() ?: 0.0 // <- Ask about this later.
        render()
    }

    fun onResetInputValueChange() {
        netSalaryString = ""
        netSalaryDouble = 0.0
        incomeAfterTax = 0.0
        taxAmount = 0.0
        sliderValue = 0.25f
        render()
    }
}
