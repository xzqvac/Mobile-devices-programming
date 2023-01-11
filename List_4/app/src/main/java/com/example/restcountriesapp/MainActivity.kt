package com.example.restcountriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.Placeholder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<CountryItem>?> {
            override fun onResponse(
                call: Call<List<CountryItem>?>,
                response: Response<List<CountryItem>?>
            ) {
                val responseBody = response.body()
            }

            override fun onFailure(call: Call<List<CountryItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+ t.message)
            }
        })
    }
}