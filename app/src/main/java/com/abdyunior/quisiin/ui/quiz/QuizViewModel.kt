package com.abdyunior.quisiin.ui.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abdyunior.quisiin.data.api.ApiConfig
import com.abdyunior.quisiin.data.response.Data
import com.abdyunior.quisiin.data.response.DataItem
import com.abdyunior.quisiin.data.response.KuesionerResponse
import com.abdyunior.quisiin.data.store.DataStorePreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizViewModel(private val pref: DataStorePreferences) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _kuesionerList = MutableLiveData<List<DataItem>>()
    val kuesionerList: LiveData<List<DataItem>> = _kuesionerList

    fun getAllKuesioner(token: String) {
        _isLoading.value = true
        val client = ApiConfig().getApiService().getAllKuesioner(token)
        client.enqueue(object : Callback<KuesionerResponse> {
            override fun onResponse(
                call: Call<KuesionerResponse>,
                response: Response<KuesionerResponse>
            ) {
                if (response.isSuccessful) {
                    _kuesionerList.postValue(response.body()?.data)
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<KuesionerResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("QuizViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getUser(): LiveData<Data> {
        return pref.getUser().asLiveData()
    }

}