package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("apikey")
    val apikey: String,
    @SerializedName("season_id")
    val seasonId: String
)