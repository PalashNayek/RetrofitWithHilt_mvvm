package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.AuthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.auth_user_response.AuthUserResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class AuthUserRepository @Inject constructor(private val authorisedAPI: AuthorisedAPI) {

    private var _authUserData= MutableLiveData<NetworkResult<AuthUserResponse>>()
    val authUserData : LiveData<NetworkResult<AuthUserResponse>> get() = _authUserData
    suspend fun authUser(){
        try {
            val response = authorisedAPI.getAuthUser()
            if (response.isSuccessful && response.body()!=null){
                _authUserData.postValue(NetworkResult.Success(response.body()))
            }else if (response.errorBody()!=null){
                val msg = JSONObject(response.errorBody()!!.charStream().readText())
                _authUserData.postValue(NetworkResult.Error(msg.getString("message")))
            }else{
                _authUserData.postValue(NetworkResult.Error("Server error"))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}