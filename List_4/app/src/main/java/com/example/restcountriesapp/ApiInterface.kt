package com.example.restcountriesapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("all")
    fun getData(): Call<List<CountryItem>>
}