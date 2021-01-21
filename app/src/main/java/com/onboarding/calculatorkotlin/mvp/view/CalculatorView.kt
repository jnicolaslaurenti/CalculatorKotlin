package com.onboarding.calculatorkotlin.mvp.view

import android.app.Activity
import android.widget.Toast
import com.onboarding.calculatorkotlin.R
import com.onboarding.calculatorkotlin.databinding.ActivityMainBinding
import com.onboarding.calculatorkotlin.mvp.CalculatorContract

class CalculatorView(activity: Activity, private var binding: ActivityMainBinding) :
    ActivityView(activity), CalculatorContract.View {

    override fun showLastValue(value: String) {
        binding.textViewResult.text = value
    }

    override fun showCompleteOperation(operation: String) {
        binding.textViewCompleteOperation.text = operation
    }

    override fun showDivisionByZeroError() {
        Toast.makeText(activity, R.string.calculator_error_division_by_zero, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showIncompleteOperationError() {
        Toast.makeText(activity, R.string.calculator_error_incomplete_operation, Toast.LENGTH_SHORT).show()
    }

    override fun resetResultView() {
        binding.textViewResult.setText(R.string.activity_main_calculator_result_text)
    }

    override fun showDeleteAllMessage() {
        Toast.makeText(activity, R.string.value_deleted, Toast.LENGTH_SHORT).show()
    }

    override fun showDeleteMessage() {
        Toast.makeText(activity, R.string.operation_deleted, Toast.LENGTH_SHORT).show()
    }

    override fun resetOperationView() {
        binding.textViewCompleteOperation.setText(R.string.activity_main_calculator_empty_operation)
    }
}
