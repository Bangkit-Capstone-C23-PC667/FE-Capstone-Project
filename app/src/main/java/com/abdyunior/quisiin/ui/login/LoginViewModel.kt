package com.abdyunior.quisiin.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdyunior.quisiin.data.store.DataStorePreferences
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: DataStorePreferences) : ViewModel() {
    fun saveUser(userId: String, name: String, token: String) {
        viewModelScope.launch {
            pref.saveUser(userId, name, token)
        }
    }
}