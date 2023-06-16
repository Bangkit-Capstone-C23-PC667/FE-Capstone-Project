package com.abdyunior.quisiin.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

@Parcelize
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
    val kuesionerId: Int?,

    @field:SerializedName("deskripsi")
    val deskripsi: String?,

    @field:SerializedName("judul")
    val judul: String?,

    @field:SerializedName("rentang_usia")
    val rentangUsia: String?,

    @field:SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable

data class CreateKuesionerResponse(

    @field:SerializedName("data")
    val data: CreateKuesionerData?,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Question(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("question")
    val question: String,

    @field:SerializedName("question_type")
    val questionType: String,

    @field:SerializedName("kuesioner_id")
    val kuesionerId: Int,

    @field:SerializedName("question_id")
    val questionId: Int,

    @field:SerializedName("updatedAt")
    val updatedAt: String,
)

data class Kuesioner(

    @field:SerializedName("jumlah_rating")
    val jumlahRating: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("kategori_id")
    val kategoriId: List<String?>,

    @field:SerializedName("rata_rata_rating")
    val rataRataRating: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("kuesioner_id")
    val kuesionerId: Int,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("rentang_usia")
    val rentangUsia: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,
)

data class CreateKuesionerData(

    @field:SerializedName("Question_list")
    val questionList: List<QuestionListItem?>,

    @field:SerializedName("kuesioner")
    val kuesioner: Kuesioner
)

data class QuestionListItem(

    @field:SerializedName("question")
    val question: Question,

    @field:SerializedName("questionOptions")
    val questionOptions: List<Any?>
)

data class UploadProfilePictureResponse(

    @field:SerializedName("message")
    val message: String?
)

data class ProfileResponse(

    @field:SerializedName("data")
    val data: ProfileData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class ProfileData(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("umur")
    val umur: Int,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("pendidikan")
    val pendidikan: String,

    @field:SerializedName("poin")
    val poin: Int,

    @field:SerializedName("asal")
    val asal: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("pekerjaan")
    val pekerjaan: String,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("hobi")
    val hobi: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)
data class UserKuesionerResponse(

    @field:SerializedName("data")
    val data: List<DataItemKuesioner>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataItemKuesioner(

    @field:SerializedName("jumlah_rating")
    val jumlahRating: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("kategori_id")
    val kategoriId: Int,

    @field:SerializedName("rata_rata_rating")
    val rataRataRating: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("kuesioner_id")
    val kuesionerId: Int,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("rentang_usia")
    val rentangUsia: Int,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)