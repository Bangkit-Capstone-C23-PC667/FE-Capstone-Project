package com.abdyunior.quisiin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abdyunior.quisiin.data.response.Data
import com.abdyunior.quisiin.data.store.DataStorePreferences

class HomeViewModel(private val preferences: DataStorePreferences) : ViewModel() {


    fun getUser(): LiveData<Data> {
        return preferences.getUser().asLiveData()
    }
}