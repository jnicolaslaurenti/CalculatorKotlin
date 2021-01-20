package com.onboarding.calculatorkotlin.mvp

import com.onboarding.calculatorkotlin.util.ConstantsUtils.Error

interface CalculatorContract {

    interface Model {
        fun setValue(value: String)
        fun setOperator(operator: String)
        fun addMinus()
        fun getLastModified(): String
        fun cleanValue()
        fun cleanAll()
        fun getError(): Error
        fun getResult(): String
        fun getOperation(): String
    }

    interface View {
        fun showLastValue(value: String)
        fun showCompleteOperation(operation: String)
        fun showDivisionByZeroError()
        fun showIncompleteOperationError()
        fun resetOperationView()
        fun resetResultView()
    }

    interface Presenter {
        fun onNumberButtonPressed(value: String)
        fun onOperatorButtonPressed(operator: String)
        fun onMinusButtonPressed()
        fun onCleanButtonPressed()
        fun onCleanButtonLongPressed()
        fun onEqualButtonPressed()
    }
}
