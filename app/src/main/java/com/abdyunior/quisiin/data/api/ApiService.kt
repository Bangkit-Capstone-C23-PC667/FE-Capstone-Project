package com.abdyunior.quisiin.data.api

import com.abdyunior.quisiin.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("users/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("kuesioners")
    fun getAllKuesioner(
        @Header("Authorization") token: String
    ): Call<KuesionerResponse>

    @Multipart
    @POST("kuesioners")
    fun createKuesioner(
        @Header("Authorization") token: String,
        @Part("link") link: RequestBody,
        @Part file: MultipartBody.Part, // Tambahkan ini untuk mengirim gambar
        @Part("judul") judul: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("rentang_usia") rentangUsia: RequestBody,
        @Part("kategori_id[0]") kategoriId: RequestBody
    ): Call<CreateKuesionerResponse>

    @GET("users/profile/kuesioner")
    fun getKuesionerByUser(
        @Header("Authorization") token: String
    ): Call<UserKuesionerResponse>

    @Multipart
    @POST("users/profile/picture")
    fun uploadProfilePicture(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Call<UploadProfilePictureResponse>

    @GET("users/profile")
    fun getProfile(
        @Header("Authorization") token: String
    ): Call<ProfileResponse>
}