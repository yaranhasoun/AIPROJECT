package com.example.aiproject

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.TextView

class OnePlayer : AppCompatActivity()  {
    private lateinit var game: Game1Player
    private lateinit var tie: TextView
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var restart: Button
    private lateinit var home: Button
    private lateinit var player1Points: TextView
    private lateinit var player2Points: TextView
    private lateinit var map: HashMap<String, TextView>

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)

        game = Game1Player()
        init()
        updatePoints()

    }

    private fun onTurnPlayed(box: TextView, position: Position) {
        if (box.text.isEmpty()) {
            box.text = game.mark

            var resLine = game.playHuman(position)
            if (resLine != null) {
                updatePoints()
                disableBoxes()
                drawWinnerLine(resLine)
            }
            if(game.isDraw()){
                updatePoints()
                disableBoxes()
                tie.visibility = View.VISIBLE;
            }
            if(!game.isDraw() && resLine==null ) {

              var p = when(game.level){
                    "hard","medium"->game.bestPos()
                    "easy"->game.randomPos()

                  else -> {game.randomPos()}
              }

                resLine = game.playAi(p)

                var s: String = p.row.toString() + p.column.toString()
                if (map[s]!!.text .isEmpty())
                    map[s]!!.text = "O"
                if (resLine != null) {
                    updatePoints()
                    disableBoxes()
                    drawWinnerLine(resLine)
                }
                if (game.isDraw()) {
                    updatePoints()
                    disableBoxes()
                    tie.visibility = View.VISIBLE;
                }
            }

        }
    }

    private fun init(){

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        restart = findViewById(R.id.restartBtn)
        home = findViewById(R.id.homeBtn)
        player1Points = findViewById(R.id.player_one_score)
        player2Points = findViewById(R.id.player_two_score)
        tie=findViewById(R.id.tie)
        tie.visibility = View.INVISIBLE;
        one.setOnClickListener { onTurnPlayed(one, Position(0, 0)) }
        two.setOnClickListener { onTurnPlayed(two, Position(0, 1)) }
        three.setOnClickListener { onTurnPlayed(three, Position(0, 2)) }
        four.setOnClickListener { onTurnPlayed(four, Position(1, 0)) }
        five.setOnClickListener { onTurnPlayed(five, Position(1, 1)) }
        six.setOnClickListener { onTurnPlayed(six, Position(1, 2)) }
        seven.setOnClickListener { onTurnPlayed(seven, Position(2, 0)) }
        eight.setOnClickListener { onTurnPlayed(eight, Position(2, 1)) }
        nine.setOnClickListener { onTurnPlayed(nine, Position(2, 2)) }
        restart.setOnClickListener {
            game.resetGame()
            tie.visibility = View.INVISIBLE;
            resetBoard()
        }
        home.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        map=
        hashMapOf("00" to one, "01" to two, "02" to three,
            "10" to four, "11" to five, "12" to six,
            "20" to seven, "21" to eight, "22" to nine
        )
        game.level= intent.getStringExtra("level").toString()

    }
    private fun drawWinnerLine(resLine: Line) {
        val (winningBoxes, background) = when (resLine) {
            Line.R0 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            Line.R1 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            Line.R2 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            Line.C0 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            Line.C1 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            Line.C2 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            Line.D1 -> Pair(listOf(one, five, nine),
                R.drawable.left_diagonal_line
            )
            Line.D2 -> Pair(listOf(three, five, seven),
                R.drawable.right_diagonal_line
            )
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(this, background)
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun resetBoard() {
        one.text = ""
        two.text = ""
        three.text = ""
        four.text = ""
        five.text = ""
        six.text = ""
        seven.text = ""
        eight.text = ""
        nine.text = ""

        one.setBackgroundResource(R.color.white1)
        two.background = null
        three.setBackgroundResource(R.color.white1)
        four.background = null
        five.setBackgroundResource(R.color.white1)
        six.background = null
        seven.setBackgroundResource(R.color.white1)
        eight.background = null
        nine.setBackgroundResource(R.color.white1)
        one.isEnabled = true
        two.isEnabled = true
        three.isEnabled = true
        four.isEnabled = true
        five.isEnabled = true
        six.isEnabled = true
        seven.isEnabled = true
        eight.isEnabled = true
        nine.isEnabled = true
    }
    private fun updatePoints() {
        player1Points.text = "Player Points: ${game.point1}"
        player2Points.text = "AI Points: ${game.point2}"
    }
    private fun disableBoxes() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }
}