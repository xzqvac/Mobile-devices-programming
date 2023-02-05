package com.example.footballstats.data.remote

import com.example.footballstats.util.API_KEY
import com.example.footballstats.util.SEASON_ID
import com.example.footballstats.util.GET_FIXTURES
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(GET_FIXTURES)
    suspend fun getFixtures(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("season_id") seasondId: Int = SEASON_ID
    )
}