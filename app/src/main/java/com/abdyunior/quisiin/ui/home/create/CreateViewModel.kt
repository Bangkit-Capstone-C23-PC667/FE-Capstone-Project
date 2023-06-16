package com.abdyunior.quisiin.ui.home.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abdyunior.quisiin.data.api.ApiConfig
import com.abdyunior.quisiin.data.response.CreateKuesionerResponse
import com.abdyunior.quisiin.data.response.Data
import com.abdyunior.quisiin.data.store.DataStorePreferences
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CreateViewModel(private val pref: DataStorePreferences) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val responseLiveData = MutableLiveData<CreateKuesionerResponse?>()

    companion object {
        const val TAG = "CreateViewModel"
    }

    fun createKuesioner(
        token: String,
        link: String,
        image: File,
        judul: String,
        deskripsi: String,
        rentangUsia: Int,
        kategoriId: Int
    ): MutableLiveData<CreateKuesionerResponse?> {
        _isLoading.value = true
        val judulBody = judul.toRequestBody("text/plain".toMediaTypeOrNull())
        val deskripsiBody = deskripsi.toRequestBody("text/plain".toMediaTypeOrNull())
        val rentangUsiaBody =
            rentangUsia.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val linkBody = link.toRequestBody("text/plain".toMediaTypeOrNull())
        val kategoriIdBody = kategoriId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData(
            "image",
            image.name,
            image.asRequestBody("image/jpeg".toMediaTypeOrNull())
        )
        val client = ApiConfig().getApiService().createKuesioner(
            token,
            linkBody,
            imagePart,
            judulBody,
            deskripsiBody,
            rentangUsiaBody,
            kategoriIdBody
        )
        client.enqueue(object : Callback<CreateKuesionerResponse> {
            override fun onResponse(
                call: Call<CreateKuesionerResponse>,
                response: Response<CreateKuesionerResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { responseLiveData.value = it }
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<CreateKuesionerResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return responseLiveData
    }

    fun getUser(): LiveData<Data> {
        return pref.getUser().asLiveData()
    }

}