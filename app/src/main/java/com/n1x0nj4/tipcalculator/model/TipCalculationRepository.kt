package com.n1x0nj4.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class TipCalculationRepository {

    private val savedTips = mutableMapOf<String, TipCalculation>()

    fun saveTipCalculation(tc: TipCalculation) {
        savedTips[tc.locationName] = tc
    }

    fun loadTipCalculatorByName(locationName: String) : TipCalculation? {
        return savedTips[locationName]
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        val liveData = MutableLiveData<List<TipCalculation>>()
        liveData.value = savedTips.values.toList()
        return liveData
    }
}