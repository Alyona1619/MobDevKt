package com.example.colortilesviewsk

import android.R.attr.x
import android.R.attr.y
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import android.widget.Toast



data class Coord(val x: Int, val y: Int)
class MainActivity : AppCompatActivity() {

    lateinit var tiles: Array<Array<View>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tiles = Array(4) { Array(4) { View(this) } }
        tiles = arrayOf(
            arrayOf(
                findViewById(R.id.t00),
                findViewById(R.id.t10),
                findViewById(R.id.t20),
                findViewById(R.id.t30)
            ),
            arrayOf(
                findViewById(R.id.t01),
                findViewById(R.id.t11),
                findViewById(R.id.t21),
                findViewById(R.id.t31)
            ),
            arrayOf(
                findViewById(R.id.t02),
                findViewById(R.id.t12),
                findViewById(R.id.t22),
                findViewById(R.id.t32)
            ),
            arrayOf(
                findViewById(R.id.t03),
                findViewById(R.id.t13),
                findViewById(R.id.t23),
                findViewById(R.id.t33)
            ),
        )

        initField()
    }

    fun getCoordFromString(s: String): Coord {
        val x = s[0].toString().toInt()
        val y = s[1].toString().toInt()
        return Coord(x, y)
    }

    fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)
        val drawable = view.background as ColorDrawable
        if (drawable.color == brightColor) {
            view.setBackgroundColor(darkColor)
        } else {
            view.setBackgroundColor(brightColor)
        }
    }

    fun onClick(v: View) {
        val coord = getCoordFromString(v.getTag().toString())
        changeColor(v)

        for (i in 0..3) {
            changeColor(tiles[coord.y][i])
            changeColor(tiles[i][coord.x])
        }
    }

    fun checkVictory(): Boolean {
        val firstTileColor = (tiles[0][0].background as ColorDrawable).color

        for (i in 0..3) {
            for (j in 0..3) {
                val currentColor = (tiles[i][j].background as ColorDrawable).color
                if (currentColor != firstTileColor) {
                    return false
                }
            }
        }
        Toast.makeText(this, "Победа!", Toast.LENGTH_SHORT).show()
        return true
    }

    fun initField() {
        val random = Random()

        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)

        for (i in 0..3) {
            for (j in 0..3) {
                val randomColor = random.nextInt(2)
                tiles[i][j].setBackgroundColor(if (randomColor == 0) darkColor else brightColor)
            }
        }
    }

    private var currentHintStep = 0

    fun solveGame() {
        val rows = tiles.size
        val cols = tiles[0].size

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if ((tiles[i][j].background as ColorDrawable).color == resources.getColor(R.color.dark)) {
                    changeColor(tiles[i][j])
                    Thread.sleep(100)
                }
            }
        }
    }

    fun restartGameButton(view: View) {
        initField()
    }

    fun completeGameButton(view: View) {
        solveGame()
        checkVictory()
    }

}