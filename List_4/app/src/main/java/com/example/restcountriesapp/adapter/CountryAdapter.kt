package com.example.restcountriesapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.R
import com.example.restcountriesapp.data.CountryItem

class CountryAdapter(activity: Activity) : RecyclerView.Adapter<CountryAdapter.CountryListViewHolder>() {

    private var countryList: List<CountryItem>? = null

    fun setCountryList(countryList: List<CountryItem>?) {
        this.countryList = countryList
    }

    class CountryListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var countryName: TextView = view.findViewById(R.id.text_view_name)
        var capital: TextView = view.findViewById(R.id.text_view_capital)

        @SuppressLint("SetTextI18n")
        fun bind(data: CountryItem){
            countryName.text = data.name.common
            if(data.capital.isNullOrEmpty())
                capital.text = "No capital"
            else{
                val capitals = buildString { data.capital.forEach { append("$it ")} }
                capital.text = capitals
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        return CountryListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_view_item, parent,false))
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        countryList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        if(countryList == null) return 0
        else return countryList!!.size
    }
}

