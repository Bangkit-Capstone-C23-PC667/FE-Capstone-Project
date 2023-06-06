package com.abdyunior.quisiin.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.abdyunior.quisiin.R
import com.abdyunior.quisiin.databinding.ActivityLoginBinding
import com.abdyunior.quisiin.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.etEmail.doOnTextChanged { text, start, before, count ->
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

        binding.etPassword.doOnTextChanged { text, start, before, count ->
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
                    //implement viewmodel
                }
            }

        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return email.matches(emailRegex)
    }
}
