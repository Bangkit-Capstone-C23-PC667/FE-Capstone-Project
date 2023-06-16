package com.abdyunior.quisiin.ui.profile.edit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.abdyunior.quisiin.data.response.ProfileData
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.ActivityEditProfileBinding
import com.abdyunior.quisiin.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var editProfileViewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editProfileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(DataStorePreferences.getInstance(dataStore), this)
        )[EditProfileViewModel::class.java]

        editProfileViewModel.getUser().observe(this) { user ->
            val token = "Bearer ${user.token}"
            editProfileViewModel.userProfile(token)
        }

        editProfileViewModel.userProfile.observe(this) { userProfile ->
            setProfileData(userProfile)
        }
    }

    private fun setProfileData(userProfile: ProfileData) {
        binding.apply {
            etName.setText(userProfile.nama)
            etEmail.setText(userProfile.email)
            etPhone.setText(userProfile.phone)
            etAge.setText(userProfile.umur.toString())
            actvGender.setText(userProfile.gender)
            actvLocation.setText(userProfile.asal)
            actvWork.setText(userProfile.pekerjaan)
            actvHobby.setText(userProfile.hobi)
        }
    }
}
