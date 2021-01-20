package com.onboarding.calculatorkotlin.mvp.view

import android.app.Activity
import com.onboarding.calculatorkotlin.databinding.ActivityMainBinding

import com.onboarding.calculatorkotlin.mvp.CalculatorContract

class CalculatorView(activity: Activity, private var binding: ActivityMainBinding) :
    ActivityView(activity), CalculatorContract.View {

    override fun showLastValue(value: String) {
        binding.textViewResult.text = value
    }

}
