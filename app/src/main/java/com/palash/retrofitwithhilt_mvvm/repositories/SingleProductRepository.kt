package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.single_product.SingleProductResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class SingleProductRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _singleProduct= MutableLiveData<NetworkResult<SingleProductResponse>>()
    val singleProductLiveData: LiveData<NetworkResult<SingleProductResponse>> get() = _singleProduct
    suspend fun singleProduct(productId: Int){
        try {
            val response = unauthorisedAPI.singleProduct(productId)
            if (response.isSuccessful && response.body()!=null){
                _singleProduct.postValue(NetworkResult.Success(response.body()))
            }else if (response.errorBody()!=null){
                val msg = JSONObject(response.errorBody()!!.charStream().readText())
                _singleProduct.postValue(NetworkResult.Error(msg.getString("message")))
            }else{
                _singleProduct.postValue(NetworkResult.Error("Server error"))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}