package com.palash.retrofitwithhilt_mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.models.product_category.ProductCategoryResponse
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(private val unauthorisedAPI: UnauthorisedAPI) {
    private var _productCategoryData = MutableLiveData<NetworkResult<ProductCategoryResponse>>()
    val productCategoryLiveData: LiveData<NetworkResult<ProductCategoryResponse>> get() = _productCategoryData
    suspend fun productCategory() {
        val response = unauthorisedAPI.getProductCategories()
        if (response.isSuccessful && response.body() != null) {
            _productCategoryData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val msg = JSONObject(response.errorBody()!!.charStream().readText())
            _productCategoryData.postValue(NetworkResult.Error(msg.getString("message")))
        } else {
            _productCategoryData.postValue(NetworkResult.Error("Server error"))
        }
    }
}