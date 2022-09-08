package com.kay.simpletaxapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kay.simpletaxapp.data.TaxViewState
import com.kay.simpletaxapp.util.sliderToPercentage

class TaxViewModel : ViewModel() {
    var viewState by mutableStateOf(TaxViewState())
        private set

    private fun render(copy: TaxViewState.() -> TaxViewState) {
        viewState = copy(viewState)
    }

    private fun calculateTotalTax(
        totalSalary: Double,
        percentage: Int
    ): Double {
        return if (
            totalSalary > 1 && totalSalary.toString().isNotEmpty()
        )
            (totalSalary * percentage) / 100
        else 0.0
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
        val taxPay = calculateTotalTax(
            totalSalary = viewState.totalSalaryAmountState.toDouble(),
            percentage = sliderToPercentage(newVal)
        )
        val salaryAfterTax = calculateSalaryAfterTax(
            totalSalary = viewState.totalSalaryAmountState.toDouble(),
            percentage = sliderToPercentage(newVal)
        )
        render {
            copy(
                totalIncomeAfterTax = salaryAfterTax,
                taxAmountState = taxPay,
                sliderPositionState = newVal,
            )
        }
    }

    fun onInputValueChange(newInputVal: String) {
        render {
            copy(
                totalSalaryAmountState = newInputVal,
            )
        }
    }
}
