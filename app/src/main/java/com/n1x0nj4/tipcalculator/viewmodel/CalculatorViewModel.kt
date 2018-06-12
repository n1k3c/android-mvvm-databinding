package com.n1x0nj4.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.n1x0nj4.tipcalculator.R
import com.n1x0nj4.tipcalculator.model.Calculator
import com.n1x0nj4.tipcalculator.model.TipCalculation

const val TAG = "CalculatorViewModel"

class CalculatorViewModel @JvmOverloads constructor(
        private val app: Application,
        private val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
    val outputTotalDollarAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
       lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
    }

    fun calculateTip() {
        // Log.d(TAG, "onClickFired")

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            // Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

    fun loadSavedTipCalculationSummaries() : LiveData<List<TipCalculationsSummaryItem>> {
        return Transformations.map(calculator.loadSaveTipCalculations(), { tipCalculationObjects ->
            tipCalculationObjects.map {
                TipCalculationsSummaryItem(it.locationName,
                        getApplication<Application>().getString(R.string.dollar_amount, it.grandTotal))
            }
        })
    }

    fun loadTipCalculation(name: String) {
        val tc = calculator.loadTipCalculationByLocationName(name)

        if (tc != null) {
            inputCheckAmount = tc.checkAmount.toString()
            inputTipPercentage = tc.tipPct.toString()

            updateOutputs(tc)
            notifyChange()
        }
    }
}