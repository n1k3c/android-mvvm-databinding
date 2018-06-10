package com.n1x0nj4.tipcalculator.model

import java.math.RoundingMode
import java.text.DecimalFormat

class Calculator {

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
}