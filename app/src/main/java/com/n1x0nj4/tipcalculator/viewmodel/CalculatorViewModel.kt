package com.n1x0nj4.tipcalculator.viewmodel

import android.databinding.BaseObservable
import android.util.Log
import com.n1x0nj4.tipcalculator.model.Calculator
import com.n1x0nj4.tipcalculator.model.TipCalculation

const val TAG = "CalculatorViewModel"

class CalculatorViewModel(private val calculator: Calculator = Calculator()) : BaseObservable() {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var tipCalculation = TipCalculation()

    fun calculateTip() {
        Log.d(TAG, "onClickFired")

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
            clearInputs()
        }
    }

    private fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }
}