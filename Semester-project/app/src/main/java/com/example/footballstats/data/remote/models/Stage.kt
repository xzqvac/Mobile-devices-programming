package com.example.footballstats.data.remote.models


import com.google.gson.annotations.SerializedName

data class Stage(
    @SerializedName("name")
    val name: String,
    @SerializedName("stage_id")
    val stageId: Int
)