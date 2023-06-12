package com.abdyunior.quisiin.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.abdyunior.quisiin.R
import com.abdyunior.quisiin.databinding.ActivityRegisterBinding
import com.abdyunior.quisiin.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val genderOptions = arrayOf("Male", "Female")
        val genderAdapter = ArrayAdapter(this, R.layout.dropdown_item, genderOptions)
        binding.actvGender.setAdapter(genderAdapter)

        binding.etName.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty() || text.isNotEmpty()) {
                binding.tilName.error = null
                binding.tilName.isErrorEnabled = false
            }
        }

        binding.etPhone.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source.toString() == "0" && binding.etPhone.text.toString().isEmpty()) {
                return@InputFilter ""
            }
            return@InputFilter source
        })

        binding.etPhone.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.tilPhone.error = null
                binding.tilPhone.isErrorEnabled = false
            } else if (text.length > 11) {
                binding.tilPhone.error = "Invalid Phone"
                binding.tilPhone.isErrorEnabled = true
            } else {
                binding.tilPhone.error = null
                binding.tilPhone.isErrorEnabled = false
            }
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

        binding.etAge.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty() || text.isNotEmpty()) {
                binding.tilAge.error = null
                binding.tilAge.isErrorEnabled = false
            }
        }

        binding.actvGender.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.tilGender.error = "Gender is required"
                binding.tilGender.isErrorEnabled = true
            } else {
                binding.tilGender.error = null
                binding.tilGender.isErrorEnabled = false
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

        binding.etConfirmPassword.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty() || text.isNotEmpty()) {
                binding.tilConfirmPassword.error = null
                binding.tilConfirmPassword.isErrorEnabled = false
            }
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val Phone = binding.etPhone.text.toString()
            val email = binding.etEmail.text.toString()
            val age = binding.etAge.text.toString()
            val gender = binding.actvGender.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            when {
                name.isEmpty() -> {
                    binding.tilName.error = "Name is required"
                    binding.tilName.isErrorEnabled = true
                }
                Phone.isEmpty() -> {
                    binding.tilPhone.error = "Phone is required"
                    binding.tilPhone.isErrorEnabled = true
                }
                email.isEmpty() -> {
                    binding.tilEmail.error = "Email is required"
                    binding.tilEmail.isErrorEnabled = true
                }
                age.isEmpty() -> {
                    binding.tilAge.error = "Age is required"
                    binding.tilAge.isErrorEnabled = true
                }
                gender.isEmpty() -> {
                    binding.tilGender.error = "Gender is required"
                    binding.tilGender.isErrorEnabled = true
                }
                password.isEmpty() -> {
                    binding.tilPassword.error = "Password is required"
                    binding.tilPassword.isErrorEnabled = true
                }
                password != confirmPassword -> {
                    binding.tilConfirmPassword.error = "Password not match"
                    binding.tilConfirmPassword.isErrorEnabled = true
                }
                else -> {
                    //implement viewmodel
                }
            }

            binding.tvLogin.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return email.matches(emailRegex)
    }
}