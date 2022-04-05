package com.example.aiproject

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ChoseLevel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_level)
    }


    @SuppressLint("ResourceAsColor")
    fun start1Player(view: View){
        var easy: Button?= findViewById<Button>(R.id.easy)

        var medium: Button?= findViewById<Button>(R.id.medium)
        var hard: Button?= findViewById<Button>(R.id.hard)

        var level:String=""
        if(view ==easy) {
            level = "easy"
            easy.setBackgroundColor(R.color.red)
        }
        else if(view ==medium) {
            level = "medium"

            medium.setBackgroundColor(R.color.red)
        }
        else if(view ==hard) {
            level = "hard"
            hard.setBackgroundColor(R.color.red)
        }

        val intent = Intent(this, OnePlayer::class.java).apply {
            putExtra("level",level)

        }
        startActivity(intent)

    }

}