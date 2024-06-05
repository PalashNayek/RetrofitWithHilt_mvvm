package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.Product
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.UpdateCartRequest
import com.palash.retrofitwithhilt_mvvm.models.update_cart.response.UpdateCartResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class UpdateCartRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private val _updateCartData = MutableLiveData<NetworkResult<UpdateCartResponse>>()
    val updateCartLiveData: LiveData<NetworkResult<UpdateCartResponse>> get() = _updateCartData
    suspend fun updateCart(cartId: Int, product: Product){
        val response = unauthorisedAPI.updateCart(cartId, product)
        if (response.isSuccessful && response.body() != null) {
            _updateCartData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _updateCartData.postValue(NetworkResult.Error(msg.getString("message")))
        } else {
            _updateCartData.postValue(NetworkResult.Error("Server error"))
        }
    }
}