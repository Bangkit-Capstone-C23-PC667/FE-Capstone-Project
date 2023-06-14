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

/*data class CreateKuesionerRequest(
    val judul: String,
    val deskripsi: String,
    val rentang_usia: Int,
    val link: String,
    val image: String
)*/

data class HistoryRequest(
    val title: String,
    val description: String
)
