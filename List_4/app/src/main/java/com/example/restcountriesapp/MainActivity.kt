package com.example.restcountriesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.adapter.CountryAdapter
import com.example.restcountriesapp.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    @SuppressLint("CutPasteId")
    private fun initRecyclerView(){

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryAdapter(this)
        findViewById<RecyclerView>(R.id.recyclerView).setHasFixedSize(true)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.doApiCall()
    }
}