package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class FixturesResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("query")
    val query: Query
)