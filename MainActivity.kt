package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var tvResult: TextView

    private var currentNumber = ""
    private var operator = ""
    private var firstOperand = 0
    private var secondOperand = 0
    private var result = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.text_result)

        val numberButtons = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        for (button in numberButtons) {
            button.setOnClickListener(this)
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnEquals).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn0 -> addDigit("0")
            R.id.btn1 -> addDigit("1")
            R.id.btn2 -> addDigit("2")
            R.id.btn3 -> addDigit("3")
            R.id.btn4 -> addDigit("4")
            R.id.btn5 -> addDigit("5")
            R.id.btn6 -> addDigit("6")
            R.id.btn7 -> addDigit("7")
            R.id.btn8 -> addDigit("8")
            R.id.btn9 -> addDigit("9")

            R.id.btnAdd -> processOperator("+")
            R.id.btnSub -> processOperator("-")
            R.id.btnMul -> processOperator("x")
            R.id.btnDiv -> processOperator("/")

            R.id.btnEquals -> calculateResult()

            R.id.btnC -> resetCalculator()
        }
    }

    private fun addDigit(digit: String) {
        currentNumber += digit
        tvResult.text = currentNumber
    }

    private fun processOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            firstOperand = currentNumber.toInt()
            operator = op
            currentNumber = ""
        }
    }

    private fun calculateResult() {
        if (currentNumber.isNotEmpty()) {
            secondOperand = currentNumber.toInt()
            result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "x" -> firstOperand * secondOperand
                "/" -> if (secondOperand != 0) firstOperand / secondOperand else 0
                else -> 0
            }
            tvResult.text = result.toString()
            currentNumber = result.toString()
        }
    }

    private fun resetCalculator() {
        currentNumber = ""
        firstOperand = 0
        secondOperand = 0
        operator = ""
        result = 0
        tvResult.text = "0"
    }
}
