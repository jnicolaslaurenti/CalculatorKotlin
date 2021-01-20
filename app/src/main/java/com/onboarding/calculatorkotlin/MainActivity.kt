package com.onboarding.calculatorkotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.calculatorkotlin.databinding.ActivityMainBinding
import com.onboarding.calculatorkotlin.mvp.CalculatorContract
import com.onboarding.calculatorkotlin.mvp.model.CalculatorModel
import com.onboarding.calculatorkotlin.mvp.presenter.CalculatorPresenter
import com.onboarding.calculatorkotlin.mvp.view.CalculatorView

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: CalculatorContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this, binding))
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.buttonEquals.setOnClickListener { presenter.onEqualButtonPressed() }
        onOperatorPressed(binding.buttonAdd)
        onOperatorPressed(binding.buttonMultiply)
        onOperatorPressed(binding.buttonDivision)
        binding.buttonSubtraction.setOnClickListener { presenter.onMinusButtonPressed() }
        onNumberPressed(binding.buttonZero)
        onNumberPressed(binding.buttonOne)
        onNumberPressed(binding.buttonTwo)
        onNumberPressed(binding.buttonThree)
        onNumberPressed(binding.buttonFour)
        onNumberPressed(binding.buttonFive)
        onNumberPressed(binding.buttonSix)
        onNumberPressed(binding.buttonSeven)
        onNumberPressed(binding.buttonEight)
        onNumberPressed(binding.buttonNine)
        binding.buttonClean.setOnClickListener { presenter.onCleanButtonPressed() }
        binding.buttonClean.setOnLongClickListener {
            presenter.onCleanButtonLongPressed()
            true
        }
    }

    private fun onNumberPressed(button: Button) {
        button.setOnClickListener { presenter.onNumberButtonPressed(button.text.toString()) }
    }

    private fun onOperatorPressed(button: Button) {
        button.setOnClickListener { presenter.onOperatorButtonPressed(button.text.toString()) }
    }
}
