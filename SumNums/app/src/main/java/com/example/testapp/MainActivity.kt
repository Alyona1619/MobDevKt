package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // TODO: 1) реализовать проверку на пустые значения в полях numA, numB
    // TODO: 2) исправить разметку, чтобы можно было вводить только числа
    // TODO: 3) сделать поддержку вещественных (не целых чисел)

    fun onClick(v: View) {
        val etA = findViewById<EditText>(R.id.numA)
        val etB = findViewById<EditText>(R.id.numB)
        val tvSum = findViewById<TextView>(R.id.sum)

        val strA = etA.text.toString()
        val strB = etB.text.toString()

        if (strA.isNotEmpty() && strB.isNotEmpty()) {
            val numA = strA.toDouble()
            val numB = strB.toDouble()
            val sum = numA + numB
            val strSum = sum.toString()
            tvSum.text = strSum
        } else {
            Toast.makeText(this, "Please enter values in both fields", Toast.LENGTH_SHORT).show()
        }
    }




}