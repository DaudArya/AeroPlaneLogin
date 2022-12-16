package com.example.fpandc13.models.auth.register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String?
)