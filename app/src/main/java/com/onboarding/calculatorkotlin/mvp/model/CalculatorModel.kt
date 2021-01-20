package com.onboarding.calculatorkotlin.mvp.model

import com.onboarding.calculatorkotlin.mvp.CalculatorContract
import com.onboarding.calculatorkotlin.util.ConstantsUtils.Error

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

    override fun addMinus() {
        if ((firstOperand.isEmpty() && operator.isEmpty()) || (secondOperand.isEmpty() && operator.isNotEmpty())) {
            setValue(SUB)
        } else {
            if (firstOperand.isNotEmpty() && firstOperand != SUB)
                setOperator(SUB)
        }
    }

    override fun cleanValue() {
        if (operator.isNotEmpty() && secondOperand.isEmpty()) {
            operator = EMPTY_STRING
        } else {
            if (operator.isEmpty()) {
                    firstOperand = firstOperand.dropLast(ONE_VALUE_REMOVED)
            } else {
                    secondOperand = secondOperand.dropLast(ONE_VALUE_REMOVED)
            }
        }
    }

    override fun cleanAll() {
        firstOperand = EMPTY_STRING
        secondOperand = EMPTY_STRING
        operator = EMPTY_STRING
    }

    override fun getLastModified(): String {
        return when {
            secondOperand.isNotEmpty() -> {
                secondOperand
            }
            operator.isNotEmpty() -> {
                operator
            }
            else -> {
                firstOperand
            }
        }
    }

    private fun operandEnabled(operand: String): Boolean {
        return (operand != EMPTY_STRING && operand != SUB)
    }

    private fun operationEnabled(): Boolean {
        var operationEnabled = true
        if (getError() == Error.ERROR_DIVISION_BY_ZERO || getError() == Error.ERROR_INCOMPLETE_OPERATION) {
            operationEnabled = false
        }
        return operationEnabled
    }

    override fun getError(): Error {
        if (!operandEnabled(firstOperand) || !operandEnabled(secondOperand)) {
            return Error.ERROR_INCOMPLETE_OPERATION
        }
        if (operandEnabled(firstOperand) && (operator == DIV) && (secondOperand == ZERO_STRING)) {
            return Error.ERROR_DIVISION_BY_ZERO
        }
        return Error.NONE
    }

    private fun makeOperation(): Double {
        when (operator) {
            ADD -> {
                return firstOperand.toDouble() + secondOperand.toDouble()
            }
            SUB -> {
                return firstOperand.toDouble() - secondOperand.toDouble()

            }
            MUL -> {
                return firstOperand.toDouble() * secondOperand.toDouble()
            }
            DIV -> {
                return firstOperand.toDouble() / secondOperand.toDouble()
            }
        }
        return DEFAULT_RESULT
    }

    override fun getOperation(): String {
        return "$firstOperand $operator $secondOperand"
    }

    override fun getResult(): String {
        var result = DEFAULT_RESULT
        if (firstOperand != EMPTY_STRING) {
            result = firstOperand.toDouble()
        }
        if (operationEnabled()) {
            result = makeOperation()
        }
        return result.toString()
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val ADD = "+"
        private const val SUB = "-"
        private const val MUL = "*"
        private const val DIV = "/"
        private const val ZERO_STRING = "0"
        private const val ONE_VALUE_REMOVED = 1
        private const val DEFAULT_RESULT = 0.0
    }
}
