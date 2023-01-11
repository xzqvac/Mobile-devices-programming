package com.example.restcountriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var countryAdapter: CountryAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView).setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = linearLayoutManager

        getAllData()
    }
        private fun getAllData() {
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
                    val responseBody = response.body()!!

                    countryAdapter = CountryAdapter(baseContext, responseBody)
                    countryAdapter.notifyDataSetChanged()
                    findViewById<RecyclerView>(R.id.recyclerView).adapter = countryAdapter

                }

                override fun onFailure(call: Call<List<CountryItem>?>, t: Throwable) {
                    d("MainActivity", "onFailure: " + t.message)
                }
            })
        }
}