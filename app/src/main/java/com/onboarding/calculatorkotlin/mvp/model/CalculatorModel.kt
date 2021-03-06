package com.onboarding.calculatorkotlin.mvp.model

import com.onboarding.calculatorkotlin.mvp.CalculatorContract
import com.onboarding.calculatorkotlin.util.ConstantsUtils.Result

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

    private fun operandEnabled(operand: String) = (operand != EMPTY_STRING && operand != SUB)

    private fun operationEnabled(): Boolean {
        var operationEnabled = true
        val result = getResult()
        if (result == Result.ERROR_DIVISION_BY_ZERO || result == Result.ERROR_INCOMPLETE_OPERATION) {
            operationEnabled = false
        }
        return operationEnabled
    }

    override fun getResult(): Result {
        if (!operandEnabled(firstOperand) || !operandEnabled(secondOperand)) {
            return Result.ERROR_INCOMPLETE_OPERATION
        }
        if (operandEnabled(firstOperand) && (operator == DIV) && (secondOperand == ZERO_STRING)) {
            return Result.ERROR_DIVISION_BY_ZERO
        }
        return Result.SUCCESS
    }

    private fun makeOperation(): Double {
        return when (operator) {
            ADD -> {
                firstOperand.toDouble() + secondOperand.toDouble()
            }
            SUB -> {
                firstOperand.toDouble() - secondOperand.toDouble()

            }
            MUL -> {
                firstOperand.toDouble() * secondOperand.toDouble()
            }
            DIV -> {
                firstOperand.toDouble() / secondOperand.toDouble()
            }
            else -> DEFAULT_RESULT
        }
    }

    override fun getOperation() = "$firstOperand$operator$secondOperand"

    override fun getResultOperation(): String {
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
