package com.example.authaeroplane.ui.register

import androidx.lifecycle.*
import com.binar.gosky.data.repository.UserRepository
import com.example.authaeroplane.data.repository.AuthRepository
import com.example.authaeroplane.wrapper.Resource
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AuthRepository, private val userRepository: UserRepository) : ViewModel() {

    private var _postRegisterUserResponse = MutableLiveData<Resource<RegisterResponse>>()
    val postRegisterUserResponse: LiveData<Resource<RegisterResponse>> get() = _postRegisterUserResponse



    fun postRegisterUser(registerRequestBody: RegisterRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val registerResponse = repository.postRegisterUser(registerRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _postRegisterUserResponse.postValue(registerResponse)
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