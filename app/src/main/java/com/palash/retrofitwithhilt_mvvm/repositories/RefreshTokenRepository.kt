package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.AuthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.refresh_token.RefreshTokenRequest
import com.palash.retrofitwithhilt_mvvm.models.refresh_token.RefreshTokenResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class RefreshTokenRepository @Inject constructor(private val authorisedAPI: AuthorisedAPI) {

    private val _refreshTokenData= MutableLiveData<NetworkResult<RefreshTokenResponse>>()
    val refreshTokenLiveData: LiveData<NetworkResult<RefreshTokenResponse>> get() = _refreshTokenData

    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest){
        val response = authorisedAPI.refreshToken(refreshTokenRequest)
        if (response.isSuccessful && response.body()!=null){
            _refreshTokenData.postValue(NetworkResult.Success(response.body()))
        }else if (response.errorBody()!=null){
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _refreshTokenData.postValue(NetworkResult.Error(msg.getString("message")))
        }else{
            _refreshTokenData.postValue(NetworkResult.Error("Server error"))
        }
    }
}