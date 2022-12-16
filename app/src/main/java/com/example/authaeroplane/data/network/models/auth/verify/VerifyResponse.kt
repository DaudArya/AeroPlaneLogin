package com.example.fpandc13.models.auth.verify


import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("message")
    val message: String?
)