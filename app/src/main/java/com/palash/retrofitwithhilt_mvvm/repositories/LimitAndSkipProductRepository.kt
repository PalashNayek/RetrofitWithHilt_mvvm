package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.limit_skip_product.LimitAndSkipProductResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class LimitAndSkipProductRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {

    private var _limitAndSkipProductData= MutableLiveData<NetworkResult<LimitAndSkipProductResponse>>()
    val limitAndSkipProductData: LiveData<NetworkResult<LimitAndSkipProductResponse>> get() = _limitAndSkipProductData
    suspend fun limitAndSkip(limit: Int, skip: Int, select: CharSequence){
        val response = unauthorisedAPI.limitAndSkipProduct(limit, skip, select)
        if (response.isSuccessful && response.body()!=null){
            _limitAndSkipProductData.postValue(NetworkResult.Success(response.body()))
        }else if (response.errorBody()!=null){
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _limitAndSkipProductData.postValue(NetworkResult.Error(msg.getString("message")))
        }else{
            _limitAndSkipProductData.postValue(NetworkResult.Error("Server error"))
        }
    }
}