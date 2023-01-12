package com.example.restcountriesapp.data


import com.example.restcountriesapp.data.Flags
import com.example.restcountriesapp.data.Name
import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("name")
    val name: Name
)