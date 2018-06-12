package com.n1x0nj4.tipcalculator.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode
import java.text.DecimalFormat

class Calculator(val repository: TipCalculationRepository = TipCalculationRepository()) {

    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation {

        val tipAmount = checkAmount * (tipPct.toDouble() / 100.0)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipPct = tipPct,
                tipAmount = df.format(tipAmount).toDouble(),
                grandTotal = grandTotal
        )
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculationByLocationName(locationName: String) : TipCalculation? {
        repository.loadTipCalculatorByName(locationName)
    }

    fun loadSaveTipCalculations() : LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}