package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.single_product.SingleProductResponse
import com.palash.retrofitwithhilt_mvvm.repositories.SingleProductRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(private val singleProductRepository: SingleProductRepository) :
    ViewModel() {

    val singleProductLiveData: LiveData<NetworkResult<SingleProductResponse>> get() = singleProductRepository.singleProductLiveData
    fun singleProduct(productId: Int) {
        viewModelScope.launch {
            singleProductRepository.singleProduct(productId)
        }
    }
}