package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onGuessClick(view: View) {

        val intent = Intent(this, GameActivity::class.java)
        // TODO: получить данные из полей ввода
        intent.putExtra("begin", 0)
        intent.putExtra("end", 100)
        startActivity(intent)

    }
}