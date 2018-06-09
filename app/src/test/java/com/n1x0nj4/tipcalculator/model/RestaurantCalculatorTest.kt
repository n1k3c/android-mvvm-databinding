package com.n1x0nj4.tipcalculator.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantCalculatorTest {

    private lateinit var calculator: RestaurantCalculator

    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculateTip() {

        val baseTc = TipCalculation(checkAmount = 10.00)

        val testVals = listOf(
                baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80)
        )

        testVals.forEach {

            assertEquals(it, calculator.calculateTip(it.checkAmount, it.tipPct))
        }
    }
}