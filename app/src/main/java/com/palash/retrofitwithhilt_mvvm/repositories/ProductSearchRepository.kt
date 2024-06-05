package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.seaech_product_response.ProductSearchResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class ProductSearchRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _searchProduct = MutableLiveData<NetworkResult<ProductSearchResponse>>()
    val searchProductLiveData: LiveData<NetworkResult<ProductSearchResponse>> get() = _searchProduct
    suspend fun searchProduct(productName: String){
        val response = unauthorisedAPI.searchProduct(productName)
        if (response.isSuccessful && response.body()!=null){
            _searchProduct.postValue(NetworkResult.Success(response.body()))
        }else if (response.errorBody()!=null){
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _searchProduct.postValue(NetworkResult.Error(msg.getString("message")))
        }else{
            _searchProduct.postValue(NetworkResult.Error("Server error"))
        }
    }
}