package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("country_id")
    val countryId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("venue_id")
    val venueId: Int
)