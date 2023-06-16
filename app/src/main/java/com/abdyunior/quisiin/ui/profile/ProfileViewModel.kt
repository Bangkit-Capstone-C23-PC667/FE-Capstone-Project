package com.abdyunior.quisiin.ui.profile

import androidx.lifecycle.*
import com.abdyunior.quisiin.data.api.ApiConfig
import com.abdyunior.quisiin.data.response.Data
import com.abdyunior.quisiin.data.response.ProfileResponse
import com.abdyunior.quisiin.data.response.UploadProfilePictureResponse
import com.abdyunior.quisiin.data.store.DataStorePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart

class ProfileViewModel(private val pref: DataStorePreferences) : ViewModel() {

    private val _uploadProfilePic = MutableLiveData<String?>()
    val uploadProfilePic: LiveData<String?> = _uploadProfilePic

    fun uploadProfilePic(token: String, file: MultipartBody.Part) {
        viewModelScope.launch(Dispatchers.IO) {
            val client = ApiConfig().getApiService().uploadProfilePicture(token, file)
            client.enqueue(object : Callback<UploadProfilePictureResponse> {
                override fun onResponse(
                    call: Call<UploadProfilePictureResponse>,
                    response: Response<UploadProfilePictureResponse>
                ) {
                    if (response.isSuccessful) {
                        _uploadProfilePic.postValue(response.body()?.message)
                    } else {
                        _uploadProfilePic.postValue(null)
                    }
                }

                override fun onFailure(call: Call<UploadProfilePictureResponse>, t: Throwable) {
                    _uploadProfilePic.postValue(null)
                }
            })
        }
    }

    fun getUser(): LiveData<Data> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}