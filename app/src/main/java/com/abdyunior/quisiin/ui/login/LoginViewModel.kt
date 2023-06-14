package com.abdyunior.quisiin.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdyunior.quisiin.data.api.ApiConfig
import com.abdyunior.quisiin.data.response.LoginRequest
import com.abdyunior.quisiin.data.response.LoginResponse
import com.abdyunior.quisiin.data.store.DataStorePreferences
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: DataStorePreferences) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    val status = MutableLiveData("")
    val message = MutableLiveData("")

    companion object {
        const val TAG = "LoginViewModel"
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        val request = LoginRequest(email, password)
        val client = ApiConfig().getApiService().login(request)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                when (response.code()) {
                    200 -> {
                        _loginResult.postValue(response.body())
                        message.postValue("200")
                    }
                    400 -> status.postValue("400")
                    401 -> status.postValue("401")
                    else -> status.postValue("ERROR ${response.code()} : ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

    }

    fun saveUser(token: String) {
        viewModelScope.launch {
            pref.saveUser(token)
        }
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}