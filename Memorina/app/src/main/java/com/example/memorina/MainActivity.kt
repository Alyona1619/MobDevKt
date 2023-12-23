package com.example.memorina

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var horseViews: ArrayList<ImageView>
    private var open: ImageView? = null
    private var success = 0
    private val match = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horseViews = ArrayList()
        for (i in 0 until match * 2) {
            val card = findViewById<ImageView>(resources.getIdentifier("c$i", "id", packageName))
            card.tag = i % match
            card.setOnClickListener { openCard(card) }
            horseViews.add(card)
        }
        newGame()
    }

    private fun newGame() {
        open = null
        success = 0
        randomizeCards()
        resetCards()
    }

    fun reset(view: View) {
        newGame()
    }

    private fun randomizeCards() {
        val tags = MutableList(match * 2) { it % match }
        tags.shuffle()
        horseViews.forEachIndexed { index, card ->
            card.tag = tags[index]
        }
    }

    private fun resetCards() {
        horseViews.forEach { card ->
            card.setImageResource(R.drawable.squarecat)
            card.visibility = View.VISIBLE
            card.isClickable = true
        }
    }

    private fun openCard(card: ImageView) {
        if (card == open || card.drawable == resources.getDrawable(R.drawable.squarecat, null)) {
            return
        }
        card.setImageResource(cardsImages(card.tag.toString().toInt()))
        if (open == null) {
            open = card
        } else {
            if (open!!.tag == card.tag) {
                matchFound(card)
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    hideMismatchedCards(card)
                }
            }
        }
    }

    private fun matchFound(card: ImageView) {
        success++
        open!!.isClickable = false
        card.isClickable = false
        GlobalScope.launch(Dispatchers.Main) {
            delay(500)
            makeCardsInvisible(open!!, card)
            open = null
            if (success == match) {
                showToast(R.string.end)
            }
        }
    }

    private fun hideMismatchedCards(card: ImageView) {
        open!!.setImageResource(R.drawable.squarecat)
        card.setImageResource(R.drawable.squarecat)
        open = null
    }

    private fun makeCardsInvisible(vararg cards: ImageView) {
        cards.forEach { it.visibility = View.INVISIBLE }
    }

    private fun showToast(messageResId: Int) {
        val toast = Toast.makeText(applicationContext, messageResId, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun cardsImages(tag: Int): Int {
        return when (tag) {
            0 -> R.drawable.horse1
            1 -> R.drawable.horse2
            2 -> R.drawable.horse3
            3 -> R.drawable.horse4
            4 -> R.drawable.horse5
            5 -> R.drawable.horse6
            6 -> R.drawable.horse7
            7 -> R.drawable.horse8
            else -> R.drawable.squarecat
        }
    }
}



//class MainActivity : AppCompatActivity() {
//    private lateinit var catViews: ArrayList<ImageView>
//    private var open: ImageView? = null
//    private var success = 0
//    private val match = 8
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        catViews = arrayListOf()
//        for (i in 0 until match * 2) {
//            val card = findViewById<ImageView>(resources.getIdentifier("c$i", "id", packageName))
//            card.tag = i % match
//            card.setOnClickListener { openCard(card) }
//            catViews.add(card)
//        }
//        new()
//    }
//
//    private fun new() {
//        open = null
//        success = 0
//        randomCards()
//        closeCards()
//    }
//
//    fun reset(v: View) {
//        new()
//    }
//
//    private fun randomCards() {
//        val tags = MutableList(match * 2) { it % match }
//        tags.shuffle()
//        for (i in 0 until match * 2) {
//            catViews[i].tag = tags[i]
//        }
//    }
//
//    private fun closeCards() {
//        for (card in catViews) {
//            card.setImageResource(R.drawable.squarecat)
//            card.visibility = View.VISIBLE
//            card.isClickable = true
//        }
//    }
//
//    private fun cardsImages(tag: Int): Int {
//        return when (tag) {
//            0 -> R.drawable.horse1
//            1 -> R.drawable.horse2
//            2 -> R.drawable.horse3
//            3 -> R.drawable.horse4
//            4 -> R.drawable.horse5
//            5 -> R.drawable.horse6
//            6 -> R.drawable.horse7
//            7 -> R.drawable.horse8
//            else -> R.drawable.squarecat
//        }
//    }
//
//    private fun openCard(card: ImageView) {
//        if (card == open || card.drawable == resources.getDrawable(R.drawable.squarecat, null)) {
//            return
//        }
//        card.setImageResource(cardsImages(card.tag.toString().toInt()))
//        if (open == null) {
//            open = card
//        } else {
//            if (open!!.tag == card.tag) {
//                success++
//                open!!.isClickable = false
//                card.isClickable = false
//                GlobalScope.launch(Dispatchers.Main) {
//                    delay(500) // Дополнительная задержка перед исчезновением
//                    open!!.visibility = View.INVISIBLE
//                    card.visibility = View.INVISIBLE
//                    open = null
//                    if (success == match) {
//                        val toast = Toast.makeText(applicationContext, R.string.end, Toast.LENGTH_SHORT)
//                        toast.show()
//                    }
//                }
//            } else {
//                GlobalScope.launch(Dispatchers.Main) {
//                    delay(1000)
//                    open!!.setImageResource(R.drawable.squarecat)
//                    card.setImageResource(R.drawable.squarecat)
//                    open = null
//                }
//            }
//        }
//    }
//}

