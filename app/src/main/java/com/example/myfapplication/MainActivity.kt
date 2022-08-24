package com.example.myfapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null;
    var lastNumeric: Boolean = false;
    var lastdot: Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.textView)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true;
        lastdot = false
    }

    fun onClear(view: View) {
        tvInput?.text = " ";
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastdot) {
            tvInput?.append(".");
            lastNumeric = false;
            lastdot = true;
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastdot = false
            }
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvVal=tvInput?.text.toString()
            var prefix=""
            try{


                if(tvVal.startsWith("-")){
                    prefix="-"
                    tvVal=tvVal.substring(1)
                }
                if(tvVal.contains("-")){
                    val splitVal=tvVal.split("-")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one;
                    }
                    tvInput?.text=(one.toDouble()-two.toDouble()).toString()
                }else if(tvVal.contains("+")){
                    val splitVal=tvVal.split("+")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one;
                    }
                    tvInput?.text=(one.toDouble()+two.toDouble()).toString()
                }else if(tvVal.contains("*")){
                    val splitVal=tvVal.split("*")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one;
                    }
                    tvInput?.text=(one.toDouble()*two.toDouble()).toString()
                }else if(tvVal.contains("/")){
                    val splitVal=tvVal.split("/")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one;
                    }
                    if(two.toDouble()==0.0){
                        tvInput?.text="ERROR"
                    }else{
                        tvInput?.text=(one.toDouble()/two.toDouble()).toString()
                    }
                }

            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    fun isOperatorAdded(s: String): Boolean {
        return if (s.startsWith("-")) {
            false
        } else {
            s.contains("/") || s.contains("+") || s.contains("*") || s.contains("-")
        }
    }
}