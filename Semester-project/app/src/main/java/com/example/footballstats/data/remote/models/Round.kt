package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Round(
    @SerializedName("is_current")
    val isCurrent: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("round_id")
    val roundId: Int
)