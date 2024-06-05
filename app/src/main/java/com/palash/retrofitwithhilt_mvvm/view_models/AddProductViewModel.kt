package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.add_new_product.AddNewProductRequest
import com.palash.retrofitwithhilt_mvvm.models.add_new_product.AddNewProductResponse
import com.palash.retrofitwithhilt_mvvm.repositories.AddNewProductRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val addNewProductRepository: AddNewProductRepository): ViewModel() {
    val addNewProductLiveData : LiveData<NetworkResult<AddNewProductResponse>> get() = addNewProductRepository.addNewProductLiveData
    fun addNewProduct(addNewProductRequest: AddNewProductRequest){
        viewModelScope.launch {
            addNewProductRepository.addNewProduct(addNewProductRequest)
        }
    }
}