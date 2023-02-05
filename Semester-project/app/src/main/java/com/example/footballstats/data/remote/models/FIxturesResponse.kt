package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class FIxturesResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("query")
    val query: Query
)