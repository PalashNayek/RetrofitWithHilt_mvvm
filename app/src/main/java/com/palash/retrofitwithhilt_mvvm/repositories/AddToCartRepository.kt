package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.AddNewCartRequest
import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.response.AddNewCartResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class AddToCartRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {
    private var _addToCart = MutableLiveData<NetworkResult<AddNewCartResponse>>()
    val addToCartLiveData: LiveData<NetworkResult<AddNewCartResponse>> get() = _addToCart
    suspend fun addToCard(addNewCartRequest: AddNewCartRequest) {
        val response = unauthorisedAPI.addToCart(addNewCartRequest)
        if (response.isSuccessful && response.body() != null) {
            _addToCart.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _addToCart.postValue(NetworkResult.Error(msg.getString("message")))
        } else {
            _addToCart.postValue(NetworkResult.Error("Server error"))
        }
    }
}