package com.example.authaeroplane.data.network.service


import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @POST(ApiEndPoints.POST_REGISTER)
    suspend fun postRegisterUser(
        @Body registerRequestBody: RegisterRequestBody
    ): RegisterResponse

    @POST(ApiEndPoints.POST_LOGIN)
    suspend fun postLoginUser(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse
}