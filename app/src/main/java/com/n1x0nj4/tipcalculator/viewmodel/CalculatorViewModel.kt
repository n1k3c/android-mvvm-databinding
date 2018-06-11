package com.n1x0nj4.tipcalculator.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import android.util.Log
import com.n1x0nj4.tipcalculator.R
import com.n1x0nj4.tipcalculator.model.Calculator
import com.n1x0nj4.tipcalculator.model.TipCalculation

const val TAG = "CalculatorViewModel"

class CalculatorViewModel @JvmOverloads constructor(
        private val app: Application,
        private val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip() {
        // Log.d(TAG, "onClickFired")

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            // Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            notifyChange()
        }
    }
}