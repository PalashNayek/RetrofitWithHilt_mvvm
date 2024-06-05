package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.login.request.LoginRequest
import com.palash.retrofitwithhilt_mvvm.models.login.response.LoginResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class LoginRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _loginData = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginData: LiveData<NetworkResult<LoginResponse>> get() = _loginData
    suspend fun login(loginRequest: LoginRequest) {
        val response = unauthorisedAPI.login(loginRequest)
        if (response.isSuccessful && response.body() != null) {
            _loginData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _loginData.postValue(NetworkResult.Error(msg.getString("message")))
        } else {
            _loginData.postValue(NetworkResult.Error("Server error"))
        }
    }
}