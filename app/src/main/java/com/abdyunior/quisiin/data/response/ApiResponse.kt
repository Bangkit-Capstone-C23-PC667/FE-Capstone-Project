package com.abdyunior.quisiin.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: Data?,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class RegisterResponse(

    @field:SerializedName("data")
    val data: Data?,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Data(

    @field:SerializedName("token")
    val token: String
)

data class KuesionerResponse(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataItem(

    @field:SerializedName("jumlah_rating")
    val jumlahRating: Int?,

    @field:SerializedName("image")
    val image: String?,

    @field:SerializedName("createdAt")
    val createdAt: String?,

    @field:SerializedName("kategori_id")
    val kategoriId: Int?,

    @field:SerializedName("rata_rata_rating")
    val rataRataRating: Int?,

    @field:SerializedName("user_id")
    val userId: Int?,

    @field:SerializedName("link")
    val link: String?,

    @field:SerializedName("kuesioner_id")
    val kuesionerId: Int,

    @field:SerializedName("deskripsi")
    val deskripsi: String?,

    @field:SerializedName("judul")
    val judul: String?,

    @field:SerializedName("rentang_usia")
    val rentangUsia: String?,

    @field:SerializedName("updatedAt")
    val updatedAt: String?
)