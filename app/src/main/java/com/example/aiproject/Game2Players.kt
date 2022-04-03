package com.example.aiproject

import android.icu.text.Transliterator




class Game2Players {

    private var playerTurn: Int = 1
    var point1: Int = 0
    var point2: Int = 0
    val mark: String
        get() {
            return if (playerTurn == 1) "X" else "O"
        }
    private var board = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun resetGame() {
        board = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        playerTurn = 1
    }

    private fun isEnd(): Line? {

        if (board[0][0] == playerTurn && board[0][1] == playerTurn && board[0][2] == playerTurn) {
            return Line.R0
        } else if (board[1][0] == playerTurn && board[1][1] == playerTurn && board[1][2] == playerTurn) {
            return Line.R1
        } else if (board[2][0] == playerTurn && board[2][1] == playerTurn && board[2][2] == playerTurn) {
            return Line.R2
        } else if (board[0][0] == playerTurn && board[1][0] == playerTurn && board[2][0] == playerTurn) {
            return Line.C0
        } else if (board[0][1] == playerTurn && board[1][1] == playerTurn && board[2][1] == playerTurn) {
            return Line.C1
        } else if (board[0][2] == playerTurn && board[1][2] == playerTurn && board[2][2] == playerTurn) {
            return Line.C2
        } else if (board[0][0] == playerTurn && board[1][1] == playerTurn && board[2][2] == playerTurn) {
            return Line.D1
        } else if (board[0][2] == playerTurn && board[1][1] == playerTurn && board[2][0] == playerTurn) {
            return Line.D2
        }
        return null
    }

    fun playTurn(p: Position): Line? {
        board[p.row][p.column] = playerTurn

        val resLine = isEnd()
        if (resLine == null)
            playerTurn = 3 - playerTurn
        else{
            when(playerTurn)
            {
                1->++point1
                2->++point2
            }


        }
        return resLine
    }


}

fun main() {
    var playerTurn = 1
    playerTurn *= -1 - 3
    print(playerTurn)
}