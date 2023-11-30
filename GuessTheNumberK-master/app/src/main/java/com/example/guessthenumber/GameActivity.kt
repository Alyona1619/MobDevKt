package com.example.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    var begin: Int = 0
    var end: Int = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)
        Log.d("mytag", "begin = " + begin)
        Log.d("mytag", "end = " + end)
    }

    fun onYesNoClick(view: View) {
        val tvQuestion = findViewById<TextView>(R.id.question)
        // TODO: проверить, что разница между begin end > 1
        // если осталось два соседних числа, спрашиваем Ваше число ... ?
        // TODO: в зависимости от ответа пользователя изменяем границы  диапазона
        when (view.id) {
            R.id.yes -> {
                Log.d("mytag", "yes")
                tvQuestion.text = "yes"
            }
            R.id.no -> {
                Log.d("mytag", "no")
                tvQuestion.text = "no"
            }

        }

    }


}