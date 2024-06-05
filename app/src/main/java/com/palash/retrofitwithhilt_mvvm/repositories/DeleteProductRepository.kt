package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.delete_product.DeleteProductResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class DeleteProductRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _deleteProduct = MutableLiveData<NetworkResult<DeleteProductResponse>>()
    val deleteLiveData: LiveData<NetworkResult<DeleteProductResponse>> get() = _deleteProduct
    suspend fun deleteProduct(productId: Int) {
        val response = unauthorisedAPI.deleteProduct(productId)
        if (response.isSuccessful && response.body() != null) {
            _deleteProduct.postValue(NetworkResult.Success(response.body()))
        }else if (response.errorBody()!=null){
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _deleteProduct.postValue(NetworkResult.Error(msg.getString("message")))
        }else{
            _deleteProduct.postValue(NetworkResult.Error("Server error"))
        }
    }
}