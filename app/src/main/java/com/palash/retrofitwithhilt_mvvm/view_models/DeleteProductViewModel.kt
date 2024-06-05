package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.delete_product.DeleteProductResponse
import com.palash.retrofitwithhilt_mvvm.repositories.DeleteProductRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteProductViewModel @Inject constructor(private val deleteProductRepository: DeleteProductRepository) :
    ViewModel() {
    val deleteProductLiveData: LiveData<NetworkResult<DeleteProductResponse>> get() = deleteProductRepository.deleteLiveData
    fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            deleteProductRepository.deleteProduct(productId)
        }
    }
}