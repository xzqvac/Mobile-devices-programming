package com.example.restcountriesapp


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String
)