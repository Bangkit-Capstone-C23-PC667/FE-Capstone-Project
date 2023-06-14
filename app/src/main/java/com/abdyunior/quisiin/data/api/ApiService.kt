package com.abdyunior.quisiin.data.api

import com.abdyunior.quisiin.data.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("users/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("kuesioners")
    fun getAllKuesioner(): Call<KuesionerResponse>
}