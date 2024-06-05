package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.update_product.UpdateProductResponse
import com.palash.retrofitwithhilt_mvvm.repositories.UpdateProductRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(private val updateProductRepository: UpdateProductRepository): ViewModel() {
    val updateProductLiveData: LiveData<NetworkResult<UpdateProductResponse>> get() = updateProductRepository.updateProductLiveData
    fun updateProduct(productId: Int){
        viewModelScope.launch {
            updateProductRepository.updateProduct(productId)
        }
    }
}