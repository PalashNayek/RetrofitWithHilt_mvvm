package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.login.request.LoginRequest
import com.palash.retrofitwithhilt_mvvm.models.login.response.LoginResponse
import com.palash.retrofitwithhilt_mvvm.repositories.LoginRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel(){

    val loginData: LiveData<NetworkResult<LoginResponse>> get() = loginRepository.loginData
    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            loginRepository.login(loginRequest)
        }
    }
}