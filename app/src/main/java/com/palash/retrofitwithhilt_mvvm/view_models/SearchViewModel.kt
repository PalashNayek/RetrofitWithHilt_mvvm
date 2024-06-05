package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.seaech_product_response.ProductSearchResponse
import com.palash.retrofitwithhilt_mvvm.repositories.ProductSearchRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val productSearchRepository: ProductSearchRepository): ViewModel() {

    val searchLiveData: LiveData<NetworkResult<ProductSearchResponse>> get() = productSearchRepository.searchProductLiveData
    fun searchProduct(productName: String){
        viewModelScope.launch {
            productSearchRepository.searchProduct(productName)
        }
    }
}