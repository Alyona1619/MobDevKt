package com.example.guessthenumber

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    private var begin: Int = 0
    private var end: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)

        updateQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestion() {
        val tvQuestion = findViewById<TextView>(R.id.question)
        if (end - begin > 1) {
            val middle = (begin + end) / 2
            tvQuestion.text = "Ваше число больше чем $middle?"
        } else {
            if (begin == end) {
                tvQuestion.text = "Ваше число $begin!"
                showSuccessMessageAndFinish()
            } else {
                tvQuestion.text = "Ваше число $end?"
            }
        }
    }

    private fun showSuccessMessageAndFinish() {
        Toast.makeText(this, "Ваше число угадано!", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 5000)
    }

    fun onYesNoClick(view: View) {
        when (view.id) {
            R.id.yes -> {
                Log.d("mytag", "yes")
                if (end - begin > 1) {
                    begin = (begin + end) / 2 + 1
                    updateQuestion()
                } else {
                    showSuccessMessageAndFinish()
                }
            }
            R.id.no -> {
                Log.d("mytag", "no")
                end = (begin + end) / 2
                updateQuestion()
            }
        }
    }

    fun onRestartClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

