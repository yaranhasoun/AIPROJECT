package com.example.aiproject


import kotlin.math.*

class Game1Player {
    private var playerTurn: Int = 1
    val MAX: Int = Int.MAX_VALUE
    val MIN: Int = Int.MIN_VALUE
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
    private var boardScore = arrayOf(
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
        boardScore = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        // playerTurn = 1
    }

    fun playHuman(p: Position): Line? {

        board[p.row][p.column] = 1

        val resLine = isWin()
        if (resLine != null)
        {
           point1++
        }
        else
            playerTurn = 3 - playerTurn
        return resLine
    }

    fun playAi(p: Position): Line? {
        board[p.row][p.column] = 2

        val resLine = isWin()
        if (resLine != null)
        {
            point2++
        }
        else
            playerTurn = 3 - playerTurn
        return resLine
    }


    fun bestPos():Position{
        var bestVal: Int = MAX
        var pos: Position = Position(0, 0)
        var available = availableBox()
        for (node in available) {
            var score = bestMove(node, 2, true, MIN, MAX)
            if (bestVal < score) {
                bestVal = score;
                pos = node
            }

        }

        return pos


    }

    fun isDraw(): Boolean {
        return isWin()==null &&availableBox().size==0
    }

    private fun bestMove(
        node: Position, depth: Int, isMaximizingPlayer: Boolean,
        alpha: Int, beta: Int
    ): Int {

        var innerAlpha: Int = alpha
        var innerBeta: Int = beta

        var bestVal: Int = if (isMaximizingPlayer) MIN else MAX

        if (depth == 0)
            return if (isMaximizingPlayer) {
                boardScore[node.row][node.column] = 10
                boardScore[node.row][node.column]
            } else {
                boardScore[node.row][node.column] = -10
                boardScore[node.row][node.column]
            }



        if (isMaximizingPlayer) {


            var available = availableBox()
            for (child in available) {
                board[child.row][child.column]=1
                val score: Int = bestMove(
                    child, depth - 1, !isMaximizingPlayer, alpha, beta
                )
            board[child.row][child.column]=0
                bestVal = max(bestVal, score)
                innerAlpha = max(bestVal, innerAlpha)
                if (innerBeta <= innerAlpha)
                    break


            }

            return bestVal
        } else {
            var available = availableBox()

            for (child in availableBox()!!) {
                board[child.row][child.column]=2
                val score: Int = bestMove(
                    child, depth - 1, !isMaximizingPlayer, alpha, beta
                )
                board[child.row][child.column]=0
                bestVal = min(bestVal, score)
                innerBeta = min(bestVal, innerBeta)
                if (innerBeta <= innerAlpha)
                    break

            }

            return bestVal
        }



    }

    private fun isWin(): Line? {

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


    private fun availableBox(): ArrayList<Position> {
        var available: ArrayList<Position> = arrayListOf()

        for (i in board.indices) {
            for (j in board[i].indices)
                if (board[i][j] == 0)
                    available.add(Position(i, j))

        }
        return available
    }

}

fun main() {
    var board = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 5, 0),
        intArrayOf(0, 0, 1)
    )
    for (i in board.indices) {
        for (j in board[i].indices)
            println(board[i][j])
    }
    var available: ArrayList<Position> = arrayListOf()
    print(available.size)
}