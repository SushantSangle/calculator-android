package com.joshint.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.joshint.calculator.models.MathViewModel

class MainActivity : AppCompatActivity() {

    private val model : MathViewModel = MathViewModel()
    private val inputTextView : TextView by lazy    { findViewById(R.id.intputTextView) }
    private val outputTextView : TextView by lazy   { findViewById(R.id.outputTextView) }
    private val button1 : Button by lazy            { findViewById(R.id.button1)        }
    private val button2 : Button by lazy            { findViewById(R.id.button2)        }
    private val button3 : Button by lazy            { findViewById(R.id.button3)        }
    private val button4 : Button by lazy            { findViewById(R.id.button4)        }
    private val button5 : Button by lazy            { findViewById(R.id.button5)        }
    private val button6 : Button by lazy            { findViewById(R.id.button6)        }
    private val button7 : Button by lazy            { findViewById(R.id.button7)        }
    private val button8 : Button by lazy            { findViewById(R.id.button8)        }
    private val button9 : Button by lazy            { findViewById(R.id.button9)        }
    private val button0 : Button by lazy            { findViewById(R.id.button0)        }
    private val buttonDot : Button by lazy          { findViewById(R.id.buttonDot)      }
    private val buttonMul : Button by lazy          { findViewById(R.id.buttonMul)      }
    private val buttonDiv : Button by lazy          { findViewById(R.id.buttonDiv)      }
    private val buttonAdd : Button by lazy          { findViewById(R.id.buttonAdd)      }
    private val buttonSub : Button by lazy          { findViewById(R.id.buttonSub)      }
    private val buttonBack : Button by lazy         { findViewById(R.id.buttonBack)     }
    private val buttonEqual : Button by lazy        { findViewById(R.id.buttonEqual)    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val inputObserver = Observer<String> {
            model.evaluate(it)
            inputTextView.text = it
        }
        val outputObserver = Observer<String>{
            outputTextView.text = it
        }

        bindListeners();

        model.outputString.observe(this,outputObserver)
        model.inputString.observe(this,inputObserver)

        model.outputString.value = ""
        model.inputString.value = ""
    }

    private fun bindListeners() {
        button9.setOnClickListener {
            model.handleInput('9')
        }
        button8.setOnClickListener {
            model.handleInput('8')
        }
        button7.setOnClickListener {
            model.handleInput('7')
        }

        button6.setOnClickListener {
            model.handleInput('6')
        }
        button5.setOnClickListener {
            model.handleInput('5')
        }
        button4.setOnClickListener {
            model.handleInput('4')
        }
        button3.setOnClickListener {
            model.handleInput('3')
        }
        button2.setOnClickListener {
            model.handleInput('2')
        }
        button1.setOnClickListener {
            model.handleInput('1')
        }
        button0.setOnClickListener {
            model.handleInput('0')
        }
        buttonBack.setOnClickListener {
            model.backPressed()
        }
        buttonBack.setOnLongClickListener {
            model.backPressed(1)
            true
        }
        buttonDiv.setOnClickListener {
            model.handleInput('/')
        }
        buttonMul.setOnClickListener {
            model.handleInput('*')
        }
        buttonSub.setOnClickListener {
            model.handleInput('-')
        }
        buttonAdd.setOnClickListener {
            model.handleInput('+')
        }
        buttonEqual.setOnClickListener {
            model.equalPressed()
        }
        buttonDot.setOnClickListener {
            model.handleInput('.')
        }
    }
}