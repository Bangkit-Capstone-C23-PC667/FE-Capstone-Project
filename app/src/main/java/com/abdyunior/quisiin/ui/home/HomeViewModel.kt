package com.abdyunior.quisiin.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abdyunior.quisiin.data.api.ApiConfig
import com.abdyunior.quisiin.data.response.Data
import com.abdyunior.quisiin.data.response.DataItemKuesioner
import com.abdyunior.quisiin.data.response.UserKuesionerResponse
import com.abdyunior.quisiin.data.store.DataStorePreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val preferences: DataStorePreferences) : ViewModel() {

    private val _kuesioberList = MutableLiveData<List<DataItemKuesioner>>()
    val kuesionerList: LiveData<List<DataItemKuesioner>> = _kuesioberList

    fun getAllKuesionerByUser(token: String) {
        val client = ApiConfig().getApiService().getKuesionerByUser(token)
        client.enqueue(object : Callback<UserKuesionerResponse> {
            override fun onResponse(
                call: Call<UserKuesionerResponse>,
                response: Response<UserKuesionerResponse>
            ) {
                if (response.isSuccessful) {
                    _kuesioberList.postValue(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<UserKuesionerResponse>, t: Throwable) {
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getUser(): LiveData<Data> {
        return preferences.getUser().asLiveData()
    }
}