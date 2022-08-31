package com.kay.simpletaxapp.util

fun calculateTotalTax(
    totalSalary: Double, /* salaryAmount */
    percentage: Int
): Double {
    return if (
        totalSalary > 1 && totalSalary.toString().isNotEmpty()
    )
        (totalSalary * percentage) / 100
    else 0.0
}

fun calculateSalaryAfterTax(
    totalSalary: Double,
    percentage: Int
): Double {
    val salaryAfterTax = calculateTotalTax(
        totalSalary = totalSalary,
        percentage = percentage
    )
    return (totalSalary - salaryAfterTax)
}
