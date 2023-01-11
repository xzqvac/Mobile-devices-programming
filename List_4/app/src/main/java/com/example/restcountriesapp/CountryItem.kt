package com.example.restcountriesapp


import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("name")
    val name: Name
)