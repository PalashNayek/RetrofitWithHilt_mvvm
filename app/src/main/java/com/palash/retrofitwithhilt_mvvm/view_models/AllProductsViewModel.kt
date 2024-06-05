package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.get_all_products.GetAllProducts
import com.palash.retrofitwithhilt_mvvm.repositories.GetAllProductsRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductsViewModel @Inject constructor(private val getAllProductsRepository: GetAllProductsRepository): ViewModel() {
    val getAllProductLiveData: LiveData<NetworkResult<GetAllProducts>> get() = getAllProductsRepository.allProductsLiveData
    fun getAllProduct(){
        viewModelScope.launch {
            getAllProductsRepository.allProducts()
        }
    }
}