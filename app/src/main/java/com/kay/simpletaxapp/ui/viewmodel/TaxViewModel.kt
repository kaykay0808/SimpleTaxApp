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
    /*private val totalSalaryAmountState: MutableState<String> = mutableStateOf("")
    private val taxAmountState: MutableState<Double> = mutableStateOf(0.0)
    private val totalIncomeAfterTax: MutableState<Double> = mutableStateOf(0.0)
    private val sliderPositionState: MutableState<Float> = mutableStateOf(0f)
    
    fun getTotalSalaryAmountState(): String {
        return totalSalaryAmountState.value
    }*/

    private fun render(copy: TaxViewState.() -> TaxViewState) {
        viewState = copy(viewState)
    }

    private fun calculateTotalTax(
        totalSalary: Double, /* salaryAmount */
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

    // private fun sliderToPercentage(value: Float): Int = (value *100).roundToInt()

    fun onSliderValueChange(newVal: Float) {
        val tax = calculateTotalTax(
            totalSalary = viewState.totalSalaryAmountState.toDouble(),
            percentage = sliderToPercentage(newVal)
        )
        val salary = calculateSalaryAfterTax(
            totalSalary = viewState.totalSalaryAmountState.toDouble(),
            percentage = sliderToPercentage(newVal)
        )
        render {
            copy(
                totalSalaryAmountState = salary.toString(),
                taxAmountState = tax,
                sliderPositionState = newVal,
            )
        }
    }

    fun onInputValueChange(newInputVal: String) {
        render {
            copy(
                totalSalaryAmountState = newInputVal
            )
        }
    }
}
