package com.onboarding.calculatorkotlin.mvp.model

import com.onboarding.calculatorkotlin.mvp.CalculatorContract
import com.onboarding.calculatorkotlin.util.Constants.ANY
import com.onboarding.calculatorkotlin.util.Constants.EMPTY_STRING
import com.onboarding.calculatorkotlin.util.Constants.MINUS
import com.onboarding.calculatorkotlin.util.Constants.ONE_VALUE_REMOVED
import com.onboarding.calculatorkotlin.util.Constants.TWO_CHARGED

class CalculatorModel : CalculatorContract.Model {

    private var firstOperand = EMPTY_STRING
    private var secondOperand = EMPTY_STRING
    private var operator = EMPTY_STRING

    override fun setValue(value: String) {
        if (operator == EMPTY_STRING) {
            firstOperand += value
        } else {
            secondOperand += value
        }
    }

    override fun setOperator(operator: String) {
        if (firstOperand != EMPTY_STRING) {
            this.operator = operator
        }
    }

    private fun getCharged(): Int {
        var charged = 0
        if (firstOperand != EMPTY_STRING) {
            charged++
            if (operator != EMPTY_STRING) {
                charged++
                if (firstOperand == EMPTY_STRING) {
                    charged++
                }
            }
        }
        return charged
    }

    override fun addMinus() {
        val charged = getCharged()
        if (charged == ANY || charged == TWO_CHARGED) {
            setValue(MINUS)
        } else {
            setOperator(MINUS)
        }
    }

    override fun cleanValue() {
        if (secondOperand.isNotEmpty()) {
            secondOperand = secondOperand.dropLast(ONE_VALUE_REMOVED)
        } else if (operator.isNotEmpty()) {
            operator = EMPTY_STRING
        }
        firstOperand = firstOperand.dropLast(ONE_VALUE_REMOVED)
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

}
