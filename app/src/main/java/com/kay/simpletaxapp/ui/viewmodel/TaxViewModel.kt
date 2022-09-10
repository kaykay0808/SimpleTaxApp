package com.kay.simpletaxapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kay.simpletaxapp.util.sliderToPercentage

class TaxViewModel : ViewModel() {
    var viewState by mutableStateOf(TaxViewState())
        private set

    var netSalaryString = ""
    var netSalary = 0.0
    var incomeAfterTax = 0.0
    var taxAmount = 0.0
    var sliderValue = 0.25f

    private fun render() {
        val taxPercent = sliderToPercentage(sliderValue)
        viewState = TaxViewState(
            netSalaryString = netSalaryString,
            netSalary = netSalary,
            incomeAfterTax = calculateSalaryAfterTax(netSalary, taxPercent),
            taxAmount = calculateTotalTax(netSalary, taxPercent),
            sliderValue = sliderValue,
            taxPercentage = taxPercent
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
        sliderValue = newVal
        render()
    }

    fun onInputValueChange(newInputVal: String) {
        netSalaryString = newInputVal
        netSalary = newInputVal.toDoubleOrNull() ?: 0.0
        render()
    }

    fun onResetInputValueChange() {
        netSalaryString = ""
        netSalary = 0.0
        incomeAfterTax = 0.0
        taxAmount = 0.0
        sliderValue = 0.25f
        render()
    }
}
