package com.example.restcountriesapp.retrofit

import com.example.restcountriesapp.data.CountryItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("all")
    fun getData(): Call<List<CountryItem>>
}