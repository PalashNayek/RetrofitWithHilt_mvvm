package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.update_product.UpdateProductResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class UpdateProductRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private val _updateProductData = MutableLiveData<NetworkResult<UpdateProductResponse>>()
    val updateProductLiveData: LiveData<NetworkResult<UpdateProductResponse>> get() = _updateProductData
    suspend fun updateProduct(productId: Int) {
        val response = unauthorisedAPI.updateProduct(productId)
        if (response.isSuccessful && response.body() != null) {
            _updateProductData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _updateProductData.postValue(NetworkResult.Error(msg.getString("message")))
        } else {
            _updateProductData.postValue(NetworkResult.Error("Server error"))
        }
    }
}