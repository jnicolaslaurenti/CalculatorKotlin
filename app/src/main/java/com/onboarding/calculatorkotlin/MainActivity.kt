package com.onboarding.calculatorkotlin

import android.os.Bundle
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
        binding.buttonAdd.setOnClickListener { presenter.onOperatorButtonPressed(binding.buttonAdd.text.toString()) }
        binding.buttonSubtraction.setOnClickListener { presenter.onMinusButtonPressed() }
        binding.buttonMultiply.setOnClickListener { presenter.onOperatorButtonPressed(binding.buttonMultiply.text.toString()) }
        binding.buttonDivision.setOnClickListener { presenter.onOperatorButtonPressed(binding.buttonDivision.text.toString()) }
        binding.buttonZero.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonZero.text.toString()) }
        binding.buttonOne.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonOne.text.toString()) }
        binding.buttonTwo.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonTwo.text.toString()) }
        binding.buttonThree.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonThree.text.toString()) }
        binding.buttonFour.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonFour.text.toString()) }
        binding.buttonFive.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonFive.text.toString()) }
        binding.buttonSix.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonSix.text.toString()) }
        binding.buttonSeven.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonSeven.text.toString()) }
        binding.buttonEight.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonEight.text.toString()) }
        binding.buttonNine.setOnClickListener { presenter.onNumberButtonPressed(binding.buttonNine.text.toString()) }
        binding.buttonClean.setOnClickListener { presenter.onCleanButtonPressed() }
        binding.buttonClean.setOnLongClickListener {
            presenter.onCleanButtonLongPressed()
            true
        }
    }
}
