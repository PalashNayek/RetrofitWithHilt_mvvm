package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.get_all_products.GetAllProducts
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class GetAllProductsRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _allAllProduct= MutableLiveData<NetworkResult<GetAllProducts>>()
    val allProductsLiveData : LiveData<NetworkResult<GetAllProducts>> get() = _allAllProduct
    suspend fun allProducts(){
        val response = unauthorisedAPI.getAllProducts()
        if (response.isSuccessful && response.body()!=null){
            _allAllProduct.postValue(NetworkResult.Success(response.body()))
        }else if (response.errorBody()!=null){
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _allAllProduct.postValue(NetworkResult.Error(msg.getString("message")))
        }else{
            _allAllProduct.postValue(NetworkResult.Error("Server error"))
        }
    }
}