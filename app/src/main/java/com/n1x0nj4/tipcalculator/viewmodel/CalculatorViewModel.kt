package com.n1x0nj4.tipcalculator.viewmodel

import com.n1x0nj4.tipcalculator.model.Calculator
import com.n1x0nj4.tipcalculator.model.TipCalculation

class CalculatorViewModel(private val calculator: Calculator = Calculator()) {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var tipCalculation = TipCalculation()

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
        }
    }
}