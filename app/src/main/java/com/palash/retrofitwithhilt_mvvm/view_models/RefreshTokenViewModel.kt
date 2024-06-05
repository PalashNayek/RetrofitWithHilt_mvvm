package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.refresh_token.RefreshTokenRequest
import com.palash.retrofitwithhilt_mvvm.models.refresh_token.RefreshTokenResponse
import com.palash.retrofitwithhilt_mvvm.repositories.RefreshTokenRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RefreshTokenViewModel @Inject constructor(private val refreshTokenRepository: RefreshTokenRepository): ViewModel() {

    val refreshTokenLiveData: LiveData<NetworkResult<RefreshTokenResponse>> get() = refreshTokenRepository.refreshTokenLiveData
    fun refreshToken(refreshTokenRequest: RefreshTokenRequest){
        viewModelScope.launch {
            refreshTokenRepository.refreshToken(refreshTokenRequest)
        }
    }
}