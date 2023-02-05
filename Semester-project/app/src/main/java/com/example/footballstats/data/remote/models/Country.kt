package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("continent")
    val continent: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("country_id")
    val countryId: Int,
    @SerializedName("name")
    val name: String
)