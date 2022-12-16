package com.example.authaeroplane.ui.login

import androidx.lifecycle.*
import com.example.authaeroplane.data.repository.AuthRepository
import com.binar.gosky.data.repository.UserRepository
import com.example.authaeroplane.wrapper.Resource
import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository, private val userRepository: UserRepository): ViewModel() {

    private var _postLoginUserResponse = MutableLiveData<Resource<LoginResponse>>()
    val postLoginUserResponse: LiveData<Resource<LoginResponse>> get() = _postLoginUserResponse

    fun postLoginUser(loginRequestBody: LoginRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val loginResponse = authRepository.postLoginUser(loginRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _postLoginUserResponse.postValue(loginResponse)
            }
        }
    }

    fun setUserLogin(isLogin: Boolean) {
        viewModelScope.launch {
            userRepository.setUserLogin(isLogin)
        }
    }

    fun getUserLoginStatus(): LiveData<Boolean> {
        return userRepository.getUserLoginStatus().asLiveData()
    }
}