package com.example.authaeroplane.data.repository

import com.binar.gosky.data.network.datasource.AuthRemoteDataSource
import com.example.authaeroplane.wrapper.Resource
import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import javax.inject.Inject

interface AuthRepository {

    suspend fun postRegisterUser(registerRequestBody: RegisterRequestBody): Resource<RegisterResponse>
    suspend fun postLoginUser(loginRequestBody: LoginRequestBody): Resource<LoginResponse>
}

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthRemoteDataSource) :
    AuthRepository {


    override suspend fun postRegisterUser(registerRequestBody: RegisterRequestBody): Resource<RegisterResponse> {
        return proceed {
            dataSource.postRegister(registerRequestBody)
        }
    }

    override suspend fun postLoginUser(loginRequestBody: LoginRequestBody): Resource<LoginResponse> {
        return proceed {
            dataSource.postLogin(loginRequestBody)
        }
    }

    private suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }/* catch (httpE: HttpException) {
            val response = httpE.response()?.errorBody()?.string()
            Resource.Error(httpE, httpE.response()?.message())
        }*/
    }
}