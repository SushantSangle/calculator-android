package com.joshint.calculator.models
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.kaen.dagger.ExpressionParser

class MathViewModel: ViewModel() {
    private val validMathExpression : Regex = Regex("^-?[0-9]+(\\.[0-9]+)?(([+\\-*/])[0-9]+(\\.[0-9]+)?)*")
    val inputString : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val outputString: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun handleInput(input : Char) {
        if (inputString.value == "infinity") backPressed(1)
        if (input.isDigit()) {
            inputString.value = "${inputString.value ?: ""}$input"
            return
        }

        if(validMathExpression.matches(inputString.value ?: "")){

            inputString.value = "${inputString.value ?: ""}$input"

            if(input == '.' && !validMathExpression.matches("${inputString.value}1")){
                inputString.value = inputString.value!!.dropLast(1)
            }
            return
        }
        if(input == '-' && inputString.value == ""){
            inputString.value = "${inputString.value ?: ""}$input"
        }
    }
    fun equalPressed(){
        if(outputString.value == "infinity"){
            inputString.value = ""
        }else{
            inputString.value = outputString.value
            outputString.value = ""
        }
    }
    fun backPressed(i : Int = 0){
        val input = inputString.value
        if(i == 0){
            inputString.value = if( (input?.length ?: 0) > 0) input?.dropLast(1) else ""
        }
    }
    fun evaluate(input : String){
        if(!validMathExpression.matches(input))
            return
        val parser = ExpressionParser()
        outputString.value = parser.evaluate(input).toString()
    }
}