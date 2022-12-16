package com.example.fpandc13.models.auth.register


import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)