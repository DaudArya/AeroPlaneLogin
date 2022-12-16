package com.binar.gosky.data.network.datasource



import com.example.authaeroplane.data.network.service.AuthApiService
import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import com.example.fpandc13.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.models.auth.verify.VerifyResponse
import com.example.fpandc13.service.auth.Interface.AeroplaneAuthApiInterface
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun postRegister(registerRequestBody: RegisterRequestBody): RegisterResponse
    suspend fun postLogin(loginRequestBody: LoginRequestBody): LoginResponse
    suspend fun getVerify(VerifyRequestBody: VerifyRequestBody): VerifyResponse
}

class AuthRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneAuthApiInterface) :
    AuthRemoteDataSource {


    override suspend fun postRegister(registerRequestBody: RegisterRequestBody): RegisterResponse {
        return apiService.postRegister(registerRequestBody)
    }
    override suspend fun postLogin(loginRequestBody: LoginRequestBody): LoginResponse {
        return apiService.postLogin(loginRequestBody)
    }

    override suspend fun getVerify(VerifyRequestBody: VerifyRequestBody): VerifyResponse {
        return apiService.getVerify(VerifyRequestBody)
    }


}