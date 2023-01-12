package com.example.restcountriesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.data.CountryItem

class CountryAdapter(val context: Context, val countryList: List<CountryItem>)
    : RecyclerView.Adapter<CountryAdapter.CountryListViewHolder>() {

    class CountryListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.text_view_name)
        var capital: TextView = view.findViewById(R.id.text_view_capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        return CountryListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.country_view_item, parent,false))
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        val country = countryList[position]
        holder.name.text = country.name.toString()
        holder.capital.text = country.capital.toString()
    }

    override fun getItemCount() = countryList.size
}

