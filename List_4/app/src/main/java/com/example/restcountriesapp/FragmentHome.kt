package com.example.restcountriesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.adapter.CountryAdapter
import com.example.restcountriesapp.databinding.FragmentHomeBinding
import com.example.restcountriesapp.viewModel.MainActivityViewModel

class FragmentHome: Fragment() {
    private lateinit var recyclerAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

          return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("NotifyDataSetChanged", "CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.fragmentRecyclerViewHome)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val viewModel:MainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.doApiCall()
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null){
                recyclerView.adapter = CountryAdapter(it, showFlags = false)
            }
        })
    }
}
