package com.abdyunior.quisiin.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.ui.home.HomeViewModel
import com.abdyunior.quisiin.ui.home.create.CreateViewModel
import com.abdyunior.quisiin.ui.login.LoginViewModel
import com.abdyunior.quisiin.ui.profile.ProfileViewModel
import com.abdyunior.quisiin.ui.quiz.QuizViewModel

class ViewModelFactory(private val pref: DataStorePreferences, private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(pref) as T
            }
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> {
                QuizViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(pref) as T
            }
            modelClass.isAssignableFrom(CreateViewModel::class.java) -> {
                CreateViewModel(pref) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}