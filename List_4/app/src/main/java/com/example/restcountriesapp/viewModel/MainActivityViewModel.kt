package com.example.restcountriesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restcountriesapp.data.CountryItem
import com.example.restcountriesapp.retrofit.ApiInterface
import com.example.restcountriesapp.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivityViewModel: ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<CountryItem>>
    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CountryItem>> {
        return liveDataList
    }

    fun doApiCall() {
        val retroInstance = RetroInstance.createRetroInstance()
        val retroService = retroInstance.create(ApiInterface::class.java)
        val call = retroService.getData()

        call.enqueue(object : retrofit2.Callback<List<CountryItem>> {
            override fun onResponse(
                call: Call<List<CountryItem>>,
                response: Response<List<CountryItem>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryItem>>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }
}