package com.abdyunior.quisiin.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.abdyunior.quisiin.MainActivity
import com.abdyunior.quisiin.R
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.ActivityLoginBinding
import com.abdyunior.quisiin.ui.home.HomeFragment
import com.abdyunior.quisiin.ui.register.RegisterActivity
import com.abdyunior.quisiin.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DataStorePreferences.getInstance(dataStore), this)
        )[LoginViewModel::class.java]

        viewModel.let {
            it.loginResult.observe(this) { login ->
                viewModel.saveUser(login.data?.token ?: "")
            }
            it.message.observe(this) { message ->
                if (message == "200") {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            it.status.observe(this) { status ->
                when (status) {
                    "400" -> {
                        Toast.makeText(this, "Email or Password is wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                    "401" -> {
                        Toast.makeText(this, "Email or Password is wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                    "404" -> {
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            it.isLoading.observe(this) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.etEmail.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.tilEmail.error = null
                binding.tilEmail.isErrorEnabled = false
                binding.tilEmail.endIconDrawable = null
            } else if (!isValidEmail(text.toString())) {
                binding.tilEmail.error = "Invalid Email"
                binding.tilEmail.isErrorEnabled = true
                binding.tilEmail.errorIconDrawable =
                    ContextCompat.getDrawable(this, R.drawable.ic_error)
            } else {
                binding.tilEmail.error = null
                binding.tilEmail.isErrorEnabled = false
                binding.tilEmail.endIconDrawable = null
            }
        }

        binding.etPassword.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.tilPassword.error = null
                binding.tilPassword.isErrorEnabled = false
                binding.tilPassword.errorIconDrawable = null
            } else if (text.length < 8) {
                binding.tilPassword.error = "Password must be at least 8 characters"
                binding.tilPassword.isErrorEnabled = true
                binding.tilPassword.errorIconDrawable = null
            } else {
                binding.tilPassword.error = null
                binding.tilPassword.isErrorEnabled = false
                binding.tilPassword.errorIconDrawable = null
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                email.isEmpty() -> {
                    binding.tilEmail.error = "Email is required"
                    binding.tilEmail.isErrorEnabled = true
                    binding.tilEmail.errorIconDrawable =
                        ContextCompat.getDrawable(this, R.drawable.ic_error)
                }
                password.isEmpty() -> {
                    binding.tilPassword.error = "Password is required"
                    binding.tilPassword.isErrorEnabled = true
                    binding.tilPassword.errorIconDrawable =
                        ContextCompat.getDrawable(this, R.drawable.ic_error)
                }
                else -> {
                    viewModel.login(email, password)
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return email.matches(emailRegex)
    }
}
