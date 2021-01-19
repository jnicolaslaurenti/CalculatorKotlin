package com.onboarding.calculatorkotlin.mvp

interface CalculatorContract {

    interface Model {
        fun setValue(value: String)
        fun setOperator(operator: String)
        fun addMinus()
        fun getLastModified(): String
        fun cleanValue()
        fun cleanAll()
    }

    interface View {
        fun showLastValue(value: String)
    }

    interface Presenter {
        fun onNumberButtonPressed(value: String)
        fun onOperatorButtonPressed(operator: String)
        fun onMinusButtonPressed()
        fun onCleanButtonPressed()
        fun onCleanButtonLongPressed()
    }
}
