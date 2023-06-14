package com.abdyunior.quisiin.data.response

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val nama: String,
    val phone: String,
    val email: String,
    val umur: Int,
    val gender: String,
    val password: String,
    val confirm_password: String
)
