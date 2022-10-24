package com.example.physicsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cheat_activity)

        createBackButon()


        val answer = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.txt_cheat_answer).apply {
            text = answer
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createBackButon(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}