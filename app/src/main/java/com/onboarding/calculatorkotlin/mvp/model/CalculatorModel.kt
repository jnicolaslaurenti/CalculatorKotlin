package com.onboarding.calculatorkotlin.mvp.model

import com.onboarding.calculatorkotlin.mvp.CalculatorContract

class CalculatorModel : CalculatorContract.Model {

    private var firstOperand = EMPTY_STRING
    private var secondOperand = EMPTY_STRING
    private var operator = EMPTY_STRING

    override fun setValue(value: String) {
        if (operator.isEmpty()) {
            firstOperand += value
        } else {
            secondOperand += value
        }
    }

    override fun setOperator(operator: String) {
        this.operator = operator
    }

    override fun cleanValue() {
        if (secondOperand.isNotEmpty()) {
            secondOperand = secondOperand.dropLast(ONE_VALUE_REMOVED)
        } else if (operator.isNotEmpty()) {
            operator = EMPTY_STRING
        }
        firstOperand = firstOperand.dropLast(ONE_VALUE_REMOVED)
    }

    override fun addMinus() {
        if ((firstOperand.isEmpty() && operator.isEmpty()) || (secondOperand.isEmpty() && operator.isNotEmpty())) {
            setValue(MINUS)
        } else {
            setOperator(MINUS)
        }
    }

    override fun cleanAll() {
        firstOperand = EMPTY_STRING
        secondOperand = EMPTY_STRING
        operator = EMPTY_STRING
    }

    override fun getLastModified(): String {
        if (secondOperand.isNotEmpty()) {
            return secondOperand
        } else if (operator.isNotEmpty()) {
            return operator
        }
        return firstOperand
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val MINUS = "-"
        private const val ONE_VALUE_REMOVED = 1
    }
}
