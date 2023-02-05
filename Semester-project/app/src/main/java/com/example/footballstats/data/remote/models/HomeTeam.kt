package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class HomeTeam(
    @SerializedName("common_name")
    val commonName: String,
    @SerializedName("country")
    val country: Country,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("short_code")
    val shortCode: String,
    @SerializedName("team_id")
    val teamId: Int
)