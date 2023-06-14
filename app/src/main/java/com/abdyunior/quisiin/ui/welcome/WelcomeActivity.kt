package com.abdyunior.quisiin.ui.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.abdyunior.quisiin.MainActivity
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.ActivityWelcomeBinding
import com.abdyunior.quisiin.ui.login.LoginActivity
import com.abdyunior.quisiin.ui.profile.ProfileViewModel
import com.abdyunior.quisiin.ui.register.RegisterActivity
import com.abdyunior.quisiin.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DataStorePreferences.getInstance(dataStore), this)
        )[ProfileViewModel::class.java]
        viewModel.getUser().observe(this) { data ->
            if (data.token.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}