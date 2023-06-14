package com.abdyunior.quisiin.ui.profile

import androidx.lifecycle.*
import com.abdyunior.quisiin.data.response.Data
import com.abdyunior.quisiin.data.store.DataStorePreferences
import kotlinx.coroutines.launch

class ProfileViewModel(private val pref: DataStorePreferences) : ViewModel() {

    fun getUser(): LiveData<Data> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}