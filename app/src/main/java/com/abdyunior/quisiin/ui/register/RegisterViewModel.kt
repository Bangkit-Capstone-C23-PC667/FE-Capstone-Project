package com.abdyunior.quisiin.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdyunior.quisiin.data.api.ApiConfig
import com.abdyunior.quisiin.data.response.RegisterRequest
import com.abdyunior.quisiin.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _registerResult = MutableLiveData<RegisterResponse>()
    val registerResult: LiveData<RegisterResponse> = _registerResult

    val status = MutableLiveData("")
    val message = MutableLiveData("")

    companion object {
        const val TAG = "RegisterViewModel"
    }

    fun register(
        nama: String,
        phone: String,
        email: String,
        umur: Int,
        gender: String,
        password: String,
        confirm_password: String
    ) {
        _isLoading.value = true
        val request = RegisterRequest(nama, phone, email, umur, gender, password, confirm_password)
        val client = ApiConfig().getApiService().register(request)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                when (response.code()) {

                    400 -> status.postValue("400")
                    201 -> message.postValue("201")
                    else -> status.postValue("ERROR ${response.code()} : ${response.errorBody()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}