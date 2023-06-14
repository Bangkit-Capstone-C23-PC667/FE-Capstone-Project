package com.abdyunior.quisiin.data.api

import com.abdyunior.quisiin.data.response.LoginRequest
import com.abdyunior.quisiin.data.response.LoginResponse
import com.abdyunior.quisiin.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("nama") nama: String,
        @Field("umur") umur: Int,
        @Field("gender") gender: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ): Call<RegisterResponse>
}