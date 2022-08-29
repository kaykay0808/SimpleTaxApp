package com.kay.simpletaxapp.util

fun calculateSalaryAfterTax(
    totalSalary: Double, /* salaryAmount */
    percentage: Int
): Double {
    return if (
        totalSalary > 1 && totalSalary.toString().isNotEmpty()
    )
        (totalSalary * percentage) / 100
    else 0.0
}

fun calculateAmountAfterTax(
    totalSalary: Double,
    percentage: Int
): Double {
    val salaryAfterTax = calculateSalaryAfterTax(
        totalSalary = totalSalary,
        percentage = percentage
    ) - percentage
    return (salaryAfterTax)
}