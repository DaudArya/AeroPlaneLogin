package com.example.fpandc13.models.auth.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?
)