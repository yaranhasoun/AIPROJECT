package com.example.aiproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onBackPressed() {


    }

    fun start2Players(view: View){
        val intent = Intent(this, TwoPlayers::class.java)
        startActivity(intent)

    }
    fun start1Player(view: View){

        val intent = Intent(this, ChoseLevel::class.java)
        startActivity(intent)

    }

}