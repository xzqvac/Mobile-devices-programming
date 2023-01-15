package com.example.restcountriesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restcountriesapp.FragmentFlags
import com.example.restcountriesapp.R
import com.example.restcountriesapp.data.CountryItem

class CountryAdapter(private val countryList: List<CountryItem>?, private val showFlags: Boolean) : RecyclerView.Adapter<CountryAdapter.CountryListViewHolder>() {

    class CountryListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var countryName: TextView = view.findViewById(R.id.text_view_name)
        var capital: TextView = view.findViewById(R.id.text_view_capital)
        var flag: ImageView = view.findViewById(R.id.imageFlag)

        @SuppressLint("SetTextI18n")
        fun bind(data: CountryItem, showFlags: Boolean){
            countryName.text = data.name.common
            if(showFlags){
                Glide.with(itemView)
                    .load(data.flags.png)
                    .into(flag)
            }
            else {
                if (data.capital.isNullOrEmpty())
                    capital.text = "No capital"
                else {
                    val capitals = buildString { data.capital.forEach { append("$it\n") } }
                    capital.text = capitals
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        return CountryListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_view_item, parent,false))
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        countryList?.get(position)?.let { holder.bind(it, showFlags) }
    }

    override fun getItemCount(): Int {
        if(countryList == null) return 0
        else return countryList!!.size
    }
}

