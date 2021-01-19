package com.onboarding.calculatorkotlin.mvp.presenter

import com.onboarding.calculatorkotlin.mvp.CalculatorContract

class CalculatorPresenter(
    private val model: CalculatorContract.Model,
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {

    override fun onNumberButtonPressed(value: String) {
        model.setValue(value)
        view.showLastValue(model.getLastModified())
    }

    override fun onOperatorButtonPressed(operator: String) {
        model.setOperator(operator)
        view.showLastValue(model.getLastModified())
    }

    override fun onMinusButtonPressed() {
        model.addMinus()
        view.showLastValue(model.getLastModified())
    }

    override fun onCleanButtonPressed() {
        model.cleanValue()
        view.showLastValue(model.getLastModified())
    }

    override fun onCleanButtonLongPressed() {
        model.cleanAll()
        view.showLastValue(model.getLastModified())
    }

}
