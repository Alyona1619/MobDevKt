package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGuessClick(view: View) {
        val beginEditText = findViewById<EditText>(R.id.begin)
        val endEditText = findViewById<EditText>(R.id.end)

        val beginValue = beginEditText.text.toString().toInt()
        val endValue = endEditText.text.toString().toInt()
        Log.d("mytag", "onGuessClick: beginValue = $beginValue, endValue = $endValue")

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("begin", beginValue)
        intent.putExtra("end", endValue)
        startActivity(intent)

    }
}