package com.onboarding.calculatorkotlin.mvp.presenter

import com.onboarding.calculatorkotlin.mvp.CalculatorContract
import com.onboarding.calculatorkotlin.util.ConstantsUtils.Error

class CalculatorPresenter(
    private val model: CalculatorContract.Model,
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {

    override fun onNumberButtonPressed(value: String) {
        model.setValue(value)
        view.showCompleteOperation(model.getOperation())
        view.showLastValue(model.getLastModified())
    }

    override fun onOperatorButtonPressed(operator: String) {
        model.setOperator(operator)
        view.showCompleteOperation(model.getOperation())
        view.showLastValue(model.getLastModified())
    }

    override fun onMinusButtonPressed() {
        model.addMinus()
        view.showCompleteOperation(model.getOperation())
        view.showLastValue(model.getLastModified())
    }

    override fun onCleanButtonPressed() {
        model.cleanValue()
        view.showCompleteOperation(model.getOperation())
        view.showLastValue(model.getLastModified())
        view.showDeleteMessage()
    }

    override fun onCleanButtonLongPressed() {
        model.cleanAll()
        view.resetOperationView()
        view.resetResultView()
        view.showDeleteAllMessage()
    }

    override fun onEqualButtonPressed() {
        val result = model.getResult()
        val error = model.getError()
        model.cleanAll()
        when (error) {
            Error.NONE -> {
                model.setValue(result)
                view.showLastValue(result)
                view.showCompleteOperation(result)
            }
            Error.ERROR_DIVISION_BY_ZERO -> {
                view.showDivisionByZeroError()
                view.resetResultView()
                view.resetOperationView()
            }
            Error.ERROR_INCOMPLETE_OPERATION -> {
                view.showIncompleteOperationError()
                view.resetResultView()
                view.resetOperationView()
            }
        }
    }
}
