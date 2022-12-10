package com.example.studenthardlife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studenthardlife.databinding.FragmentViewItemsBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { FragmentViewItemsBinding.inflate(layoutInflater) }
    private val dbHandler by lazy { DBHandler(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}