package com.onboarding.calculatorkotlin

import com.nhaarman.mockitokotlin2.mock
import com.onboarding.calculatorkotlin.mvp.CalculatorContract
import com.onboarding.calculatorkotlin.mvp.model.CalculatorModel
import com.onboarding.calculatorkotlin.mvp.presenter.CalculatorPresenter
import com.onboarding.calculatorkotlin.util.ConstantsUtils.Error
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class CalculatorTest {
    private lateinit var model: CalculatorContract.Model
    private lateinit var presenter: CalculatorContract.Presenter
    private var view: CalculatorContract.View = mock()

    @Before
    fun setup() {
        model = CalculatorModel()
        presenter = CalculatorPresenter(model, view)
    }

    @Test
    fun setACorrectOperator() {
        presenter.onOperatorButtonPressed(ADD)
        assertEquals(ADD, model.getLastModified())
        verify(view).showCompleteOperation(model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun setValuesInFirstOperand() {
        presenter.onNumberButtonPressed(ONE_STRING)
        assertEquals(ONE_STRING, model.getLastModified())
        verify(view).showCompleteOperation(model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun setValuesInSecondOperand() {
        model.setValue(ONE_STRING)
        model.setOperator(ADD)
        presenter.onNumberButtonPressed(TWO_STRING)
        assertEquals(TWO_STRING, model.getLastModified())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showCompleteOperation(model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun CleanAllTheOperation() {
        presenter.onCleanButtonLongPressed()
        assertEquals(EMPTY_STRING, model.getOperation())
        verify(view).resetResultView()
        verify(view).showDeleteAllMessage()
        verify(view).resetOperationView()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun CleanAValueInSecondOperand() {
        model.setValue(ONE_STRING)
        model.setOperator(MUL)
        model.setValue(TWO_STRING)
        model.setValue(THREE_STRING)
        presenter.onCleanButtonPressed()
        verify(view).showCompleteOperation(model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showDeleteMessage()
        assertEquals(OPERATION_CLEAN_A_VALUE_IN_SECOND_OPERAND, model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun CleanAValueInFirstOperand() {
        model.setValue(ONE_STRING)
        model.setValue(TWO_STRING)
        presenter.onCleanButtonPressed()
        verify(view).showCompleteOperation(model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showDeleteMessage()
        assertEquals(ONE_STRING, model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun CleanOperator() {
        model.setValue(ONE_STRING)
        model.setOperator(MUL)
        presenter.onCleanButtonPressed()
        verify(view).showCompleteOperation(model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showDeleteMessage()
        assertEquals(ONE_STRING, model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun minusInFirstOperand() {
        presenter.onMinusButtonPressed()
        assertEquals(SUB, model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showCompleteOperation(model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun minusInOperator() {
        model.setValue(ONE_STRING)
        presenter.onMinusButtonPressed()
        assertEquals(OPERATION_MINUS_IN_OPERATOR, model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showCompleteOperation(model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun minusInSecondOperand() {
        model.setValue(ONE_STRING)
        model.setOperator(MUL)
        presenter.onMinusButtonPressed()
        assertEquals(OPERATION_MINUS_IN_SECOND_OPERAND, model.getOperation())
        verify(view).showLastValue(model.getLastModified())
        verify(view).showCompleteOperation(model.getOperation())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun operationWithoutErrors() {
        model.setValue(ONE_STRING)
        model.setOperator(MUL)
        model.setValue(THREE_STRING)
        presenter.onEqualButtonPressed()
        assertEquals(THREE_RESULT_DOUBLE, model.getResult())
        verify(view).showLastValue(model.getResult())
        verify(view).showCompleteOperation(model.getResult())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun ErrorByDivisionByZero() {
        model.setValue(ONE_STRING)
        model.setOperator(DIV)
        model.setValue(ZERO_STRING)
        val error = model.getError()
        presenter.onEqualButtonPressed()
        assertEquals(Error.ERROR_DIVISION_BY_ZERO, error)
        verify(view).showDivisionByZeroError()
        verify(view).resetResultView()
        verify(view).resetOperationView()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun ErrorIncompleteOperation() {
        model.setValue(ONE_STRING)
        model.setOperator(DIV)
        val error = model.getError()
        presenter.onEqualButtonPressed()
        assertEquals(Error.ERROR_INCOMPLETE_OPERATION, error)
        verify(view).showIncompleteOperationError()
        verify(view).resetResultView()
        verify(view).resetOperationView()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun emptyOperator() {
        model.setValue(ONE_STRING)
        assertEquals(ONE_RESULT_DOUBLE, model.getResult())
    }

    @Test
    fun addOperator() {
        model.setValue(ONE_STRING)
        model.setOperator(ADD)
        model.setValue(TWO_STRING)
        assertEquals(THREE_RESULT_DOUBLE, model.getResult())
    }

    @Test
    fun subtractionOperator() {
        model.setValue(THREE_STRING)
        model.setOperator(SUB)
        model.setValue(ONE_STRING)
        assertEquals(TWO_RESULT_DOUBLE, model.getResult())
    }

    @Test
    fun divisionOperator() {
        model.setValue(FOUR_STRING)
        model.setOperator(DIV)
        model.setValue(TWO_STRING)
        assertEquals(TWO_RESULT_DOUBLE, model.getResult())
    }

    @Test
    fun concatenateValuesInFirstOperand() {
        model.setValue(ONE_STRING)
        assertEquals(ONE_STRING, model.getLastModified())
        model.setValue(ONE_STRING)
        assertEquals(ELEVEN_STRING, model.getLastModified())
        model.setValue(ONE_STRING)
        assertEquals(ONE_HUNDRED_ELEVEN_STRING, model.getLastModified())
    }

    @Test
    fun concatenateValuesInSecondOperand() {
        model.setValue(ONE_STRING)
        model.setOperator(MUL)
        model.setValue(ONE_STRING)
        assertEquals(ONE_STRING, model.getLastModified())
        model.setValue(ONE_STRING)
        assertEquals(ELEVEN_STRING, model.getLastModified())
        model.setValue(ONE_STRING)
        assertEquals(ONE_HUNDRED_ELEVEN_STRING, model.getLastModified())
    }

    @Test
    fun replaceOperator() {
        model.setValue(ONE_STRING)
        model.setOperator(MUL)
        model.setValue(TWO_STRING)
        model.setOperator(ADD)
        assertEquals(OPERATION_REPLACE_OPERATOR, model.getOperation())
    }

    @Test
    fun correctlyReturnsTheLastModified() {
        model.setValue(ONE_STRING)
        assertEquals(ONE_STRING, model.getLastModified())
        model.setOperator(MUL)
        assertEquals(MUL, model.getLastModified())
        model.setValue(TWO_STRING)
        assertEquals(TWO_STRING, model.getLastModified())
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val ADD = "+"
        private const val SUB = "-"
        private const val MUL = "*"
        private const val DIV = "/"
        private const val ZERO_STRING = "0"
        private const val ONE_STRING = "1"
        private const val TWO_STRING = "2"
        private const val THREE_STRING = "3"
        private const val FOUR_STRING = "4"
        private const val ELEVEN_STRING = "11"
        private const val ONE_HUNDRED_ELEVEN_STRING = "111"
        private const val ONE_RESULT_DOUBLE = "1.0"
        private const val TWO_RESULT_DOUBLE = "2.0"
        private const val THREE_RESULT_DOUBLE = "3.0"
        private const val OPERATION_MINUS_IN_SECOND_OPERAND = "1*-"
        private const val OPERATION_MINUS_IN_OPERATOR = "1-"
        private const val OPERATION_CLEAN_A_VALUE_IN_SECOND_OPERAND = "1*2"
        private const val OPERATION_REPLACE_OPERATOR = "1+2"
    }
}