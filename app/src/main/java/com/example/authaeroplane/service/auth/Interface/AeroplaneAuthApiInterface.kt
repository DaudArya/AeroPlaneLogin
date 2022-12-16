package com.example.fpandc13.service.auth.Interface

import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import com.example.fpandc13.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.models.auth.verify.VerifyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AeroplaneAuthApiInterface {
    @POST("api/auth/register")
    suspend fun postRegister(
        @Body registerRequestBody: RegisterRequestBody
    ): RegisterResponse

    @POST("api/auth/login")
    suspend fun postLogin(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

    @GET("api/auth/verify")
    suspend fun getVerify(
        @Body verifyRequestBody: VerifyRequestBody
    ): VerifyResponse
}