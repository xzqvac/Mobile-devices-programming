package com.example.restcountriesapp.data


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String
)