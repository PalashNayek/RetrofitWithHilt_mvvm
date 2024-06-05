package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.product_category.ProductCategoryResponse
import com.palash.retrofitwithhilt_mvvm.repositories.ProductCategoryRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(private val productCategoryRepository: ProductCategoryRepository): ViewModel() {

    val productCategoryLiveData: LiveData<NetworkResult<ProductCategoryResponse>> get() = productCategoryRepository.productCategoryLiveData
    fun getProductCategory(){
        viewModelScope.launch {
            productCategoryRepository.productCategory()
        }
    }
}