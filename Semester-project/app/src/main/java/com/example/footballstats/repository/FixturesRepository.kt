package com.example.footballstats.repository

import com.example.footballstats.data.remote.APIService
import com.example.footballstats.data.remote.models.Data
import javax.inject.Inject

class FixturesRepository @Inject constructor(private val sportDataAPIService: APIService){
    suspend fun getLiveFixtures(): List<Data> = sportDataAPIService.getLiveFixtures().data

    suspend fun getFutureFixtures(): List<Data> = sportDataAPIService.getFixtures().data
}
