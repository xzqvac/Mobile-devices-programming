package com.example.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val showCount: TextView by lazy{findViewById(R.id.show_count)}
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null)
            count = savedInstanceState.getInt("counter_state")

        showCount.text = String.format(Locale.GERMAN, "%,d", count)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_state", count)
    }

    fun countUpButton(view: View?)
    {
        count++;
        showCount.text = count.toString();
    }
}