package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.add_new_product.AddNewProductRequest
import com.palash.retrofitwithhilt_mvvm.models.add_new_product.AddNewProductResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class AddNewProductRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _addNewProduct = MutableLiveData<NetworkResult<AddNewProductResponse>>()
    val addNewProductLiveData: LiveData<NetworkResult<AddNewProductResponse>> get() = _addNewProduct

    suspend fun addNewProduct(addNewProductRequest: AddNewProductRequest) {
        val response = unauthorisedAPI.productAdd(addNewProductRequest)
        if (response.isSuccessful && response.body() != null){
            _addNewProduct.postValue(NetworkResult.Success(response.body()))
        }else if (response.errorBody()!=null){
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _addNewProduct.postValue(NetworkResult.Error(msg.getString("message")))
        }else{
            _addNewProduct.postValue(NetworkResult.Error("server error"))
        }
    }
}