package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.auth_user_response.AuthUserResponse
import com.palash.retrofitwithhilt_mvvm.repositories.AuthUserRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAuthUserViewModel @Inject constructor(private val authUserRepository: AuthUserRepository): ViewModel() {
    val authUserLiveData: LiveData<NetworkResult<AuthUserResponse>> get() = authUserRepository.authUserData
    fun getAuthUser(){
        viewModelScope.launch {
            authUserRepository.authUser()
        }
    }
}