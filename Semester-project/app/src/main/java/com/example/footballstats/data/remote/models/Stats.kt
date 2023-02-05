package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("away_score")
    val awayScore: Int,
    @SerializedName("et_score")
    val etScore: Any,
    @SerializedName("ft_score")
    val ftScore: String,
    @SerializedName("home_score")
    val homeScore: Int,
    @SerializedName("ht_score")
    val htScore: String,
    @SerializedName("ps_score")
    val psScore: Any
)